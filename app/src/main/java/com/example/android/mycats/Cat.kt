package com.example.android.mycats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_table")
data class Cat (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cat_name")
    var id : Int,

    @ColumnInfo(name = "subscriber_id")
    var name : String,

    @ColumnInfo(name = "subscriber_email")
    var email : String

)