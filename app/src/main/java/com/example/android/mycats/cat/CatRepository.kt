package com.example.android.mycats.cat

import com.example.android.mycats.database.Cat
import com.example.android.mycats.database.CatDAO

class CatRepository(private val dao : CatDAO) {

    val cats = dao.getAllCats()

    suspend fun insert(cat: Cat):Long{
        return dao.insertCat(cat)
    }

    suspend fun update(cat: Cat):Int{
        return dao.updateCat(cat)
    }

    suspend fun delete(cat: Cat) : Int{
        return dao.deleteCat(cat)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }
}