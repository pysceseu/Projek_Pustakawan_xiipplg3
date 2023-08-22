package com.salma.projek_pustakawan_xiipplg3

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.salma.projek_pustakawan_xiipplg3.databinding.ActivityInputPustakawanBinding
import com.salma.projek_pustakawan_xiipplg3.db.dbPustakawan
import com.salma.projek_pustakawan_xiipplg3.db.pustakawan
import java.text.SimpleDateFormat
import java.util.*

class input_pustakawan : AppCompatActivity() {

    private lateinit var binding: ActivityInputPustakawanBinding
    private lateinit var database: dbPustakawan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputPustakawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = dbPustakawan.getInstance(applicationContext)

        binding.btnsave.setOnClickListener {
            if (binding.edId.text.isNotEmpty() &&
                binding.ednama.text.isNotEmpty() &&
                binding.edtgl.text.isNotEmpty() &&
                binding.edstatus.text.isNotEmpty()
            ) {

                database.pustakawanDao().inputData(
                    pustakawan(
                        binding.edId.text.toString().toInt(),
                        binding.ednama.text.toString(),
                        binding.edtgl.text.toString(),
                        binding.edstatus.text.toString()
                    )
                )
                binding.edId.setText("")
                binding.ednama.setText("")
                binding.edtgl.setText("")
                binding.edstatus.setText("")

                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            } else {
                Toast.makeText(
                    applicationContext,
                    "silahkan lengkapi semua data terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        this.setTanggalRegister()
    }
    private fun setTanggalRegister() {
        this.setTanggal()
        binding.edtgl.setOnClickListener {
            var cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { picker, tahun, bulan, tanggal ->
                    binding.edtgl.setText("" + tanggal + "-" + bulan + "-" + tahun)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }
        private fun setTanggal () {
            val calendar = Calendar.getInstance()
            val simpleDateFormat = SimpleDateFormat("d-M-yyyy")
            val dateTime = simpleDateFormat.format(calendar.time)
            binding.edtgl.setText(dateTime)
        }}

