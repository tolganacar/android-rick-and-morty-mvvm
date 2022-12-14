package com.tolganacar.rickmorty.viewmodel.rmcharacterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.rickmorty.model.RMCharacter

class RMCharacterDetailViewModel : ViewModel() {

    val characterLiveData = MutableLiveData<RMCharacter>()

    fun setCharacter(character: RMCharacter) {
        characterLiveData.value = character
    }
}