package com.tolganacar.rickmorty.view.rmcharacterlist

import android.view.View
import com.tolganacar.rickmorty.model.RMCharacter

interface RMCharacterClickListener {

    fun onRMCharacterClicked(character: RMCharacter)
}