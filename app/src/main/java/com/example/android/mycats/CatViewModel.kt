package com.example.android.mycats

import android.util.Log
import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class CatViewModel(private val repository: CatRepository) : ViewModel(), Observable {

    val cats = repository.cats
    private var isUpdateOrDelete = false
    private lateinit var catToUpdateOrDelete: Cat


    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputSpecies = MutableLiveData<String>()

    @Bindable
    val inputAge = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {

        if (inputName.value == null) {
            statusMessage.value = Event("Please enter the cat name")
        } else if (inputSpecies.value == null) {
            statusMessage.value = Event("Please enter the cat species")
        }  else {
            if (isUpdateOrDelete) {
                catToUpdateOrDelete.name = inputName.value!!
                catToUpdateOrDelete.species = inputSpecies.value!!
                update(catToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val species = inputSpecies.value!!
                val age: String = inputAge.value!!
                insert(Cat(0, name, species, age))
                inputName.value = null
                inputSpecies.value = null
                inputAge.value=null
            }
        }


    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(catToUpdateOrDelete)
        } else {
            clearAll()
        }

    }

    fun insert(subscriber: Cat) = viewModelScope.launch {
        val newRowId = repository.insert(subscriber)
        if (newRowId > -1) {
            statusMessage.value = Event("Subscriber Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun update(subscriber: Cat) = viewModelScope.launch {
        val noOfRows = repository.update(subscriber)
        if (noOfRows > 0) {
            inputName.value = null
            inputSpecies.value = null
            inputAge.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun delete(subscriber: Cat) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(subscriber)

        if (noOfRowsDeleted > 0) {
            inputName.value = null
            inputSpecies.value = null
            inputAge.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun clearAll() = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted Subscribers Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun initUpdateAndDelete(cat: Cat) {
        inputName.value = cat.name
        inputSpecies.value = cat.species
        inputAge.value= cat.age
        isUpdateOrDelete = true
        catToUpdateOrDelete = cat
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}