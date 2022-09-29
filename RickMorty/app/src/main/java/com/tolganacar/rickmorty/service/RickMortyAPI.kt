package com.tolganacar.rickmorty.service

import com.tolganacar.rickmorty.model.CharacterResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface RickMortyAPI {

    @GET("/character")
    fun getCharacters() : Single<List<CharacterResponseModel>>
}