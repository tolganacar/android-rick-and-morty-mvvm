package com.tolganacar.rickmorty.service

import com.google.gson.JsonElement
import com.tolganacar.rickmorty.model.RMCharacterResponseModel
import com.tolganacar.rickmorty.model.RMLocationResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickMortyAPI {

    @GET("character")
    fun getRMCharacters(): Single<RMCharacterResponseModel>

    @GET(NEXT_PAGE_CHARACTER_LIST)
    fun getNextRMCharacters(@Query(PAGE) page: String): Single<RMCharacterResponseModel>

    @GET("tolganacar/rickandmorty-location-dataset/main/rmlocationdataset.json")
    fun getRMLocations(): Single<List<RMLocationResponseModel>>

    @GET("http://service.com/movies/list")
    fun getMovieList(@Query("movie_lang") userLanguage: String?): Single<JsonElement?>?

    companion object {
        const val PAGE = "page"
        const val NEXT_PAGE_CHARACTER_LIST = "character/"
    }
}