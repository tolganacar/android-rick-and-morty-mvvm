package com.tolganacar.rickmorty.service

import com.tolganacar.rickmorty.model.RMCharacterResponseModel
import com.tolganacar.rickmorty.model.RMLocationResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface RickMortyAPI {

    @GET("character")
    fun getRMCharacters(): Single<RMCharacterResponseModel>

    @GET("tolganacar/rickandmorty-location-dataset/main/rmlocationdataset.json")
    fun getRMLocations(): Single<List<RMLocationResponseModel>>
}