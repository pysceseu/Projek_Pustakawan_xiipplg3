package com.salma.projek_pustakawan_xiipplg3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbPustakawan")
data class pustakawan(

    @PrimaryKey
    @ColumnInfo(name = "id_pustakawan")
    val id: Int,

    @ColumnInfo(name = "nama_pustakawan")
    val nama: String,

    @ColumnInfo(name = "status_pustakawan")
    val status: String,

    @ColumnInfo(name = "tanggal_registrasi")
    val tanggal: String
)
