package com.example.layout

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.layout.databinding.ActivityDetailProductBinding
import java.text.NumberFormat
import java.util.Locale

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding
    private var jumlah = 1
    private var hargaSatuan: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tangkap data dari Intent
        val nama = intent.getStringExtra("EXTRA_NAME") ?: "Produk"
        val hargaString = intent.getStringExtra("EXTRA_PRICE") ?: "0"
        val gambar = intent.getIntExtra("EXTRA_IMAGE", 0)

        hargaSatuan = hargaString.replace(Regex("[^0-9]"), "").toLongOrNull() ?: 0

        // data awal ke tampilan
        binding.tvDetailName.text = nama
        binding.tvDetailPrice.text = hargaString
        binding.imgDetail.setImageResource(gambar)
        binding.tvQuantity.text = jumlah.toString()

        // Tombol Tambah (+)
        binding.btnPlus.setOnClickListener {
            jumlah++
            updateTampilan()
        }

        //  Tombol Kurang (-)
        binding.btnMinus.setOnClickListener {
            if (jumlah > 1) {
                jumlah--
                updateTampilan()
            }
        }

        // Tombol Beli
        binding.btnBuyNow.setOnClickListener {
            val totalHarga = formatRupiah(hargaSatuan * jumlah)
            Toast.makeText(this, "Berhasil pesan $jumlah pcs $nama\nTotal: $totalHarga", Toast.LENGTH_LONG).show()
        }
    }

    // Fungsi untuk update angka jumlah dan total harga di layar
    private fun updateTampilan() {
        binding.tvQuantity.text = jumlah.toString()
        val totalHarga = hargaSatuan * jumlah
        binding.tvDetailPrice.text = formatRupiah(totalHarga)
    }

    // Fungsi pembantu untuk format angka ke Rupiah (Rp 1.000.000)
    private fun formatRupiah(number: Long): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).replace("Rp", "Rp ").replace(",00", "")
    }
}