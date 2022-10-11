package com.tolganacar.rickmorty.service

import com.tolganacar.rickmorty.model.RMCharacterResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface RickMortyAPI {

    @GET("character")
    fun getRMCharacters() : Single<RMCharacterResponseModel>
}