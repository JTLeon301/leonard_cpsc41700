package com.example.leonard.circuitsphere

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leonard.circuitsphere.model.Product

class ProductListAdapter(private var productList: List<Product>, private val clickListener: (Product) -> Unit) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener { clickListener(product) }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateList(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.title_text_view)
        private val productImageView: ImageView = itemView.findViewById(R.id.product_image)
        private val productPriceView: TextView = itemView.findViewById(R.id.price_text_view)

        fun bind(product: Product) {
            productNameTextView.text = product.name
            productImageView.setImageResource(product.imageId)
            productPriceView.text = "$${"%.2f".format(product.price)}"
        }
    }
}



