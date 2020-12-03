package com.example.android.mycats

class CatRepository(private val dao : CatDAO) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Cat):Long{
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Cat):Int{
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Cat) : Int{
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }
}