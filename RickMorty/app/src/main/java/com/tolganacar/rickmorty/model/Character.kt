package com.tolganacar.rickmorty.model

data class RMCharacterResponseModel(
    val results: List<RMCharacter>
)

data class RMCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: RMCharacterOrigin,
    val location: RMCharacterLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class RMCharacterOrigin(
    val name: String,
    val url: String
)

data class RMCharacterLocation(
    val name: String,
    val url: String,
)
