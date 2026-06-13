package com.example.labo4_pdm

import android.app.Application
import androidx.room.Room
import com.example.labo4_pdm.Model.AppDatabase

class InitDatabase : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "AppDatabase"
        ).fallbackToDestructiveMigration().build()
    }
}