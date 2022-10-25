package com.tolganacar.rickmorty.view.rmcharacterlist

import com.tolganacar.rickmorty.model.RMCharacter

interface RMCharacterClickListener {

    fun onRMCharacterClicked(character: RMCharacter)
}