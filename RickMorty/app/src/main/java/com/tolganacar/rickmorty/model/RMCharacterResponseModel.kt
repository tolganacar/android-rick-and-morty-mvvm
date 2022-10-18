package com.tolganacar.rickmorty.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

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
    val origin: @RawValue RMCharacterOrigin?,
    val location: @RawValue RMCharacterLocation?,
    val image: String?,
    val episode: List<String>?,
    val url: String?,
    val created: String?
) : Parcelable

data class RMCharacterOrigin(
    val name: String,
    val url: String
)

data class RMCharacterLocation(
    val name: String,
    val url: String,
)
