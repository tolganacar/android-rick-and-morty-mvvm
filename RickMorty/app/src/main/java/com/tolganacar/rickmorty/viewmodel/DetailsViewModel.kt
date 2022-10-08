package com.tolganacar.rickmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.rickmorty.model.Character

class DetailsViewModel: ViewModel() {

    val characterLiveData = MutableLiveData<Character>()

    fun getCharacter(){

    }
}