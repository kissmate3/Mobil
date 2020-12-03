package com.example.android.mycats

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CatDAO {

    @Insert
    suspend fun insertSubscriber(cat: Cat) : Long

    @Update
    suspend fun updateSubscriber(cat: Cat) : Int

    @Delete
    suspend fun deleteSubscriber(cat: Cat) : Int

    @Query("DELETE FROM cat_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM cat_table")
    fun getAllSubscribers():LiveData<List<Cat>>
}