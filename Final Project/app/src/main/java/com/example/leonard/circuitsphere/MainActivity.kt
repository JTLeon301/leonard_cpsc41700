package com.example.leonard.circuitsphere

import com.example.leonard.circuitsphere.model.Product
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var checkoutButton: Button
    private lateinit var totalPriceTextView: TextView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: AuthStateListener
    private var userId: String? = null
    private lateinit var productAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        firebaseAuth = FirebaseAuth.getInstance()

        recyclerView = findViewById(R.id.recycler_view)
        floatingActionButton = findViewById(R.id.fab)
        checkoutButton = findViewById(R.id.checkout_button)
        totalPriceTextView = findViewById(R.id.total_price)

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        }

        checkoutButton.setOnClickListener {
            saveCartToDatabase()
            showToast("Thank you for your purchase.")
        }

        val signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract()
        ) { result: FirebaseAuthUIAuthenticationResult ->
            if (result.resultCode == RESULT_OK) {
                val user: FirebaseUser? = firebaseAuth.currentUser
                if (user != null) {
                    userId = user.uid
                    setAdapter()
                }
            } else if (result.resultCode == RESULT_CANCELED) {
                finish()
            }
        }

        val user: FirebaseUser? = firebaseAuth.currentUser
        if (user != null) {
            userId = user.uid
            setAdapter()
        } else {
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(R.style.Base_Theme_CircuitSphere)
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(listOf(AuthUI.IdpConfig.EmailBuilder().build()))
                .build()
            signInLauncher.launch(signInIntent)
        }

        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                userId = user.uid
            } else {
                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setTheme(R.style.Base_Theme_CircuitSphere)
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(listOf(AuthUI.IdpConfig.EmailBuilder().build()))
                    .build()
                signInLauncher.launch(signInIntent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    override fun onResume() {
        super.onResume()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.signout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.sign_out) {
            AuthUI.getInstance().signOut(this)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setAdapter() {
        val cartItems = Cart.getCartItems()
        productAdapter = ProductListAdapter(cartItems) { product ->
            //Handle item click if needed
        }

        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        updateTotalPrice(cartItems)
    }

    private fun saveCartToDatabase() {
        val currentUser = firebaseAuth.currentUser
        currentUser?.uid?.let { uid ->
            val cartItems = Cart.getCartItems()
            val cartRef = FirebaseDatabase.getInstance().reference.child("carts").child(uid)
            cartRef.setValue(cartItems)
                .addOnSuccessListener {
                    Cart.clearCart()
                    productAdapter.notifyDataSetChanged()
                    updateTotalPrice(emptyList()) //Clear total price after checkout
                }
                .addOnFailureListener { e ->
                    showToast("Error saving cart: ${e.message}")
                }
        }
    }

    private fun updateTotalPrice(cartItems: List<Product>) {
        val totalPrice = cartItems.sumOf { it.price.toDouble() } //Convert String price to Double
        totalPriceTextView.text = "Total Price: $${"%.2f".format(totalPrice)}"
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


