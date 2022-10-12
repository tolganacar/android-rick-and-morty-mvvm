package com.tolganacar.rickmorty.model

import android.os.Parcel
import android.os.Parcelable

data class RMCharacterResponseModel(
    val results: List<RMCharacter>
)

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("origin"),
        TODO("location"),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(status)
        parcel.writeString(species)
        parcel.writeString(type)
        parcel.writeString(gender)
        parcel.writeString(image)
        parcel.writeStringList(episode)
        parcel.writeString(url)
        parcel.writeString(created)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RMCharacter> {
        override fun createFromParcel(parcel: Parcel): RMCharacter {
            return RMCharacter(parcel)
        }

        override fun newArray(size: Int): Array<RMCharacter?> {
            return arrayOfNulls(size)
        }
    }
}

data class RMCharacterOrigin(
    val name: String,
    val url: String
)

data class RMCharacterLocation(
    val name: String,
    val url: String,
)
