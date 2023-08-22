package com.salma.projek_pustakawan_xiipplg3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salma.projek_pustakawan_xiipplg3.adapter.AdapterPustakawan
import com.salma.projek_pustakawan_xiipplg3.databinding.ActivityMainBinding
import com.salma.projek_pustakawan_xiipplg3.db.dbPustakawan
import com.salma.projek_pustakawan_xiipplg3.db.pustakawan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val db by lazy {dbPustakawan.getInstance(this)}
    private lateinit var adapter: AdapterPustakawan
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //5.
        adapter = AdapterPustakawan(arrayListOf(),
        object : AdapterPustakawan.OnClickListener{
            //6.
            override fun onDelete(pustakawan: pustakawan) {
                deleteData(pustakawan)
            }

            override fun onEdit(pustakawan: pustakawan) {
            }

        })
        binding.listData.adapter = adapter
        binding.listData.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.listData.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))

        binding.btnadd.setOnClickListener {
            startActivity(
                Intent(this, input_pustakawan::class.java)
            )
        }
    }
    //7.
    private fun deleteData(pustakawan: pustakawan) {
        //8.peringatan
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi hapus data")
            setMessage("Anda yakin ingin menghapus data ${pustakawan.nama}?")
            setNegativeButton("Batal"){
                dialogInterface: DialogInterface, i: Int ->
                //menutup interface dialog
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus"){
                dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.pustakawanDao().hapusData(pustakawan)
                    finish()
                    startActivity(intent)
                }
                getData()
            }
            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    fun getData() {
        binding.listData.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.pustakawanDao().getAllData()
            adapter.setData(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
        binding.listData.adapter = adapter

    }
    }
