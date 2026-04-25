package com.example.layout

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.layout.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.incActionButtons.btnLaris.setOnClickListener {
            val intent = Intent(this, TerlarisActivity::class.java)
            startActivity(intent)
        }
        binding.incActionButtons.btnRakit.setOnClickListener {
            val intent = Intent(this, TerlarisActivity::class.java)
            startActivity(intent)
        }

        // SET TOOLBAR SEBAGAI ACTIONBAR
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Gaming Store"

        // HUBUNGAN DRAWERLAYOUT DENGAN TOOLBAR
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open,
            R.string.close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //SET LISTENER UNTUK KLIK MENU SAMPING
        binding.navView.setNavigationItemSelectedListener(this)

        // SETUP RECYCLERVIEW PRODUK (VERTICAL)
        val listProduk = ArrayList<Product>()
        listProduk.add(Product("Secret Lab Chair", "Rp 7.500.000", R.drawable.kursi_secret))
        listProduk.add(Product("ROG Zephyrus G14", "Rp 28.000.000", R.drawable.laptop_gaming))
        listProduk.add(Product("RTX 4090 Founders", "Rp 35.000.000", R.drawable.rtx_founder))
        listProduk.add(Product("Logitech G Pro", "Rp 1.200.000", R.drawable.mouse))
        listProduk.add(Product("Monitor Odyssey", "Rp 12.000.000", R.drawable.monitor))

        binding.rvProductsHome.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(listProduk)
        binding.rvProductsHome.adapter = adapter


        // SETUP RECYCLERVIEW KATEGORI (HORIZONTAL)
        val listKategori = ArrayList<Category>()
        listKategori.add(Category("Mouse", R.drawable.bg_login1))
        listKategori.add(Category("Keyboard", R.drawable.bg_login2))
        listKategori.add(Category("Monitor", R.drawable.bg_login1))
        listKategori.add(Category("Headset", R.drawable.bg_login2))
        listKategori.add(Category("Laptop", R.drawable.bg_login1))

        binding.incCategories.rvKategori.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val categoryAdapter = CategoryAdapter(listKategori)
        binding.incCategories.rvKategori.adapter = categoryAdapter


        if (savedInstanceState == null) {
            binding.navView.setCheckedItem(R.id.nav_home)

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> Toast.makeText(this, "Ke Dashboard", Toast.LENGTH_SHORT).show()
            R.id.nav_pc -> {
//                RAKIT PC
                val intent = Intent(this, RakitPcActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_acc -> Toast.makeText(this, "Ke Accessories", Toast.LENGTH_SHORT).show()
            R.id.nav_logout -> {
                val sharedPref = getSharedPreferences("UserAcc", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        } else {
            super.onSupportNavigateUp()
        }
    }
}