package com.tolganacar.rickmorty.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class RMCharacterResponseModel(
    val results: List<RMCharacter>
)

@Parcelize
data class RMCharacter(
    val id: Int,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val origin: RMCharacterOrigin?,
    val location: RMCharacterLocation?,
    val image: String?,
    val episode: List<String>?,
    val url: String?,
    val created: String?
) : Parcelable

@Parcelize
data class RMCharacterOrigin(
    val name: String,
    val url: String
) : Parcelable

@Parcelize
data class RMCharacterLocation(
    val name: String,
    val url: String,
) : Parcelable
