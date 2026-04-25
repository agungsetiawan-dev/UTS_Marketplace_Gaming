package com.example.layout

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.layout.databinding.ActivityRakitPcBinding // Pastikan import ini benar
import java.text.NumberFormat
import java.util.Locale

class RakitPcActivity : AppCompatActivity() {

    // binding ke activity_rakit_pc
    private lateinit var binding: ActivityRakitPcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRakitPcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Data Pilihan Komponen (Dropdown)
        val listMobo = arrayOf("Pilih Motherboard", "ASUS ROG B760 - 3jt", "MSI Pro Z790 - 4.5jt", "Gigabyte Aorus - 3.8jt")
        val listCpu = arrayOf("Pilih Processor", "Intel i5-13400F - 3.2jt", "Intel i7-13700K - 6.5jt", "AMD Ryzen 7 7700X - 5.8jt")
        val listRam = arrayOf("Pilih RAM", "Corsair 16GB - 1.2jt", "G.Skill 32GB - 2.5jt", "Kingston 8GB - 0.6jt")
        val listVga = arrayOf("Pilih VGA", "RTX 3060 - 4.5jt", "RTX 4070 - 9.5jt", "RTX 4090 - 32.5jt")

        // Pasang Adapter ke Spinner
        binding.spinMobo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listMobo)
        binding.spinCpu.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listCpu)
        binding.spinRam.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listRam)
        binding.spinVga.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listVga)

        // Tombol Hitung
        binding.btnHitungSimulasi.setOnClickListener {
            var total: Long = 0

            // Cek pilihan Motherboard
            total += when(binding.spinMobo.selectedItemPosition) {
                1 -> 3000000; 2 -> 4500000; 3 -> 3800000; else -> 0
            }
            // Cek pilihan Processor
            total += when(binding.spinCpu.selectedItemPosition) {
                1 -> 3200000; 2 -> 6500000; 3 -> 5800000; else -> 0
            }
            // Cek pilihan RAM
            total += when(binding.spinRam.selectedItemPosition) {
                1 -> 1200000; 2 -> 2500000; 3 -> 600000; else -> 0
            }
            // Cek pilihan VGA
            total += when(binding.spinVga.selectedItemPosition) {
                1 -> 4500000; 2 -> 9500000; 3 -> 32500000; else -> 0
            }

            // Tampilkan Total Harga dengan Format Rupiah
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            binding.tvTotalSimulasi.text = "Total: " + formatRupiah.format(total).replace(",00", "").replace("Rp", "Rp ")
        }
    }
}