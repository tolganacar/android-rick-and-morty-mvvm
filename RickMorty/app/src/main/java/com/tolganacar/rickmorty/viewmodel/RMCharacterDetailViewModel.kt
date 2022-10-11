package com.tolganacar.rickmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.rickmorty.model.RMCharacter

class RMCharacterDetailViewModel: ViewModel() {

    val characterLiveData = MutableLiveData<RMCharacter>()

    fun getCharacter(){

    }
}