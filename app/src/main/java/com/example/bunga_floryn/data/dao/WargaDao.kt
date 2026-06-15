package com.example.bunga_floryn.data.dao

import androidx.room.*
import com.example.bunga_floryn.data.entity.WargaEntity

@Dao
interface WargaDao {
    @Query("SELECT * FROM warga ORDER BY nama ASC")
    suspend fun getAll(): List<WargaEntity>

    @Insert
    suspend fun insert(warga: WargaEntity)

    @Delete
    suspend fun delete(warga: WargaEntity)
}
