package com.example.task_ex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){
    val list = MutableLiveData<MutableList<FactDetails>>()

    init {
        val items = mutableListOf<FactDetails>()
        list.value = items
    }
}