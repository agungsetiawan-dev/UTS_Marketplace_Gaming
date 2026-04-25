package com.example.layout

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.layout.databinding.ItemProductDashboardBinding

class ProductAdapter(private val listProduct: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProductDashboardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductDashboardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = listProduct[position]

        // Tampilkan data ke layout
        holder.binding.tvNameDashboard.text = product.name
        holder.binding.tvPriceDashboard.text = product.price
        holder.binding.imgProductDashboard.setImageResource(product.image)

        // Aksi klik Tombol Detail
        holder.binding.btnDetailDashboard.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailProductActivity::class.java)
            intent.putExtra("EXTRA_NAME", product.name)
            intent.putExtra("EXTRA_PRICE", product.price)
            intent.putExtra("EXTRA_IMAGE", product.image)

            holder.itemView.context.startActivity(intent)

            Toast.makeText(holder.itemView.context, "Membuka detail ${product.name}", Toast.LENGTH_SHORT).show()
        }


        holder.itemView.setOnClickListener {
            holder.binding.btnDetailDashboard.performClick()
        }
    }

    override fun getItemCount(): Int = listProduct.size
}