package com.example.leonard.circuitsphere

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.leonard.circuitsphere.model.Product

class ProductDetailActivity : AppCompatActivity() {
    private var product: Product? = null
    private lateinit var productNameText: TextView
    private lateinit var productPicture: ImageView
    private lateinit var productNumberText: TextView
    private lateinit var productDescriptionText: TextView
    private lateinit var productPriceText: TextView
    private lateinit var addToCartButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        productNameText = findViewById(R.id.product_name)
        productPicture = findViewById(R.id.product_picture)
        productNumberText = findViewById(R.id.product_number)
        productDescriptionText = findViewById(R.id.product_description)
        productPriceText = findViewById(R.id.product_price)

        addToCartButton = findViewById(R.id.add_to_cart_button)

        //Get the product from intent
        product = intent.getParcelableExtra("product")

        setUi()

        addToCartButton.setOnClickListener {
            //Add product to cart and navigate to MainActivity
            product?.let { Cart.addProduct(it) }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUi() {
        product?.let {
            productNameText.text = it.name
            productNumberText.text = it.partNumber
            productDescriptionText.text = it.description
            productPicture.setImageResource(it.imageId)
            productPriceText.text = "$${"%.2f".format(it.price)}"
        }
    }
}
