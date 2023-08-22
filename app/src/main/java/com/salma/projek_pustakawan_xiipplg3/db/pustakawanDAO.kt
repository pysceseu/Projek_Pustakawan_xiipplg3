package com.salma.projek_pustakawan_xiipplg3.db

import androidx.room.*

@Dao
interface pustakawanDAO {
    @Insert
    fun inputData(pustakawan: pustakawan)

    @Delete
    fun hapusData(pustakawan: pustakawan)

    @Update
    fun ubahData(pustakawan: pustakawan)

    @Query("SELECT * FROM tbPustakawan")
    fun getAllData() : List<pustakawan>
}