package com.salma.projek_pustakawan_xiipplg3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salma.projek_pustakawan_xiipplg3.R
import com.salma.projek_pustakawan_xiipplg3.db.pustakawan

class AdapterPustakawan(val list: ArrayList<pustakawan>, var listener: OnClickListener):
    RecyclerView.Adapter<AdapterPustakawan.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val id = itemView.findViewById<TextView>(R.id.txtId)
        val nama = itemView.findViewById<TextView>(R.id.txtnama)
        val tanggal = itemView.findViewById<TextView>(R.id.txtStatus)
        val status = itemView.findViewById<TextView>(R.id.txtTgl)
        val hapus = itemView.findViewById<Button>(R.id.btnhapus)
        val edit = itemView.findViewById<Button>(R.id.btnedit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = list[position].id.toString()
        holder.nama.text = list[position].nama
        holder.tanggal.text = list[position].tanggal
        holder.status.text = list[position].status
        holder.hapus.setOnClickListener {
            listener.onDelete(list[position])
        }
        holder.edit.setOnClickListener {
            listener.onEdit(list[position])
        }
    }

        fun setData(newList: List<pustakawan>) {
            list.clear()
            list.addAll(newList)
        }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onDelete(pustakawan: pustakawan)
        fun onEdit(pustakawan: pustakawan)
    }
}