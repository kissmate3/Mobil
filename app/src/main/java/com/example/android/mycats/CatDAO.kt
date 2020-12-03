package com.example.android.mycats

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CatDAO {

    @Insert
    suspend fun insertCat(cat: Cat) : Long

    @Update
    suspend fun updateCat(cat: Cat) : Int

    @Delete
    suspend fun deleteCat(cat: Cat) : Int

    @Query("DELETE FROM cat_table_2")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM cat_table_2")
    fun getAllCats():LiveData<List<Cat>>
}