package com.example.android.mycats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_table_2")
data class Cat (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cat_name")
    var id : Int,

    @ColumnInfo(name = "cat_id")
    var name : String,

    @ColumnInfo(name = "species")
    var species : String,

    @ColumnInfo(name = "age")
    var age : String


)