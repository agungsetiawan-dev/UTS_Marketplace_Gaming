package com.example.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.layout.databinding.ActivityTerlarisBinding

class TerlarisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTerlarisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerlarisBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Data Produk Terlaris
        val listTerlaris = ArrayList<Product>()
        listTerlaris.add(Product("PC Gaming Ultra", "Rp 15.000.000", R.drawable.pc_gaming))
        listTerlaris.add(Product("Laptop ROG Strix", "Rp 22.500.000", R.drawable.laptop_gaming))

        // Set ke RecyclerView
        binding.rvTerlaris.layoutManager = LinearLayoutManager(this)
        binding.rvTerlaris.adapter = ProductAdapter(listTerlaris)
    }
}