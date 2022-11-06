package com.tolganacar.rickmorty.model

import android.os.Parcelable
import com.tolganacar.rickmorty.base.ListAdapterItem
import kotlinx.android.parcel.Parcelize

data class RMCharacterResponseModel(
    val info: RMInfo,
    val results: List<RMCharacter>
)

@Parcelize
data class RMInfo(
    val next: String
) : Parcelable

@Parcelize
data class RMCharacter(
    override val id: Int,
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
) : Parcelable, ListAdapterItem

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
