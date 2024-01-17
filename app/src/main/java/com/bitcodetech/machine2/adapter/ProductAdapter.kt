package com.bitcodetech.machine2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.machine2.R
import com.bitcodetech.machine2.databinding.ProductViewsBinding
import com.bitcodetech.machine2.models.Product
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class ProductAdapter(private val products :ArrayList <Product>)
    :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()
{

    interface OnProductImageListener{
        fun onProductCLick(product: Product, position: Int, productAdapter: ProductAdapter)
    }
    var onProductImageListener : OnProductImageListener? = null

    inner class ProductViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding : ProductViewsBinding


        init {
            binding = ProductViewsBinding.bind(view)

            binding.root.setOnClickListener {
                onProductImageListener?.onProductCLick(
                    products[adapterPosition],
                    adapterPosition,
                    this@ProductAdapter
                )

            }
        }

    }

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_views,null)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = products[position]


        Glide.with(holder.itemView)
            .load(product.image)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.binding.imgProduct)

//        Picasso.get()
//            .load("https://cdn.dummyjson.com/product-images/1/thumbnail.jpg")
//            .into(holder.binding.imgProduct)

       //holder.binding.imgProduct.setImageResource(product.images.toInt())
        holder.binding.txtTitle.text = product.title
        holder.binding.txtPrice.text = product.price.toString()
        holder.binding.txtBrand.text = product.brand
        holder.binding.txtDescription.text = product.description

    }

}