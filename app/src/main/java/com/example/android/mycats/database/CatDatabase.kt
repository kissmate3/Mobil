package com.example.android.mycats.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cat::class],version = 3)
abstract class CatDatabase : RoomDatabase() {

    abstract val  catDAO: CatDAO

    companion object{
        @Volatile
        private var INSTANCE : CatDatabase? = null
        fun getInstance(context: Context): CatDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CatDatabase::class.java,
                        "cat_data_database3"
                    ).build()
                }
                return instance
            }
        }

    }
}