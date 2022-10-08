package com.tolganacar.rickmorty.service

import com.tolganacar.rickmorty.model.Character
import com.tolganacar.rickmorty.model.RMCharacterResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RickMortyAPIService {
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(RickMortyAPI::class.java)

    fun getCharacterList(): Single<RMCharacterResponseModel>{
        return api.getCharacters()
    }
}