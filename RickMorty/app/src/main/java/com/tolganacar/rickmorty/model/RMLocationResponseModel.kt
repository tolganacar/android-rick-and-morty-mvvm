package com.tolganacar.rickmorty.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RMLocationResponseModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val image: String
) : Parcelable