package com.example.leonard.circuitsphere

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leonard.circuitsphere.model.Product
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ProductAdapter(options: FirebaseRecyclerOptions<Product>, val clickListener: (Int) -> Unit):
    FirebaseRecyclerAdapter<Product, ProductAdapter.ProductViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater, parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int, model: Product) {
        holder.bind(model)
        holder.itemView.setOnClickListener{clickListener(position)}
    }

    inner class ProductViewHolder(inflater: LayoutInflater, parent: ViewGroup?):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
        private val productTitleView: TextView = itemView.findViewById(R.id.title_text_view)

        fun bind(product: Product) {
            productTitleView.text = product.name
        }
    }
}