package com.salma.projek_pustakawan_xiipplg3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [pustakawan::class], version = 1)
    abstract class dbPustakawan : RoomDatabase() {
    abstract fun pustakawanDao(): pustakawanDAO

    companion object{

    @Volatile
    private var INSTANCE: dbPustakawan? = null
    private var key = Any()
    @Synchronized
    fun getInstance(context: Context): dbPustakawan {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, dbPustakawan::class.java, "pustakawan SA")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
        return INSTANCE!!
    }

    }
}