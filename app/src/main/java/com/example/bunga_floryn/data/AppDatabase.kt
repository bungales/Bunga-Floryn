package com.example.bunga_floryn.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bunga_floryn.data.dao.NoteDao
import com.example.bunga_floryn.data.dao.WargaDao
import com.example.bunga_floryn.data.entity.NoteEntity
import com.example.bunga_floryn.data.entity.WargaEntity

@Database(
    entities = [NoteEntity::class, WargaEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun wargaDao(): WargaDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
