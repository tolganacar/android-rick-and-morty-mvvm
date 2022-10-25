package com.tolganacar.rickmorty.service

import com.tolganacar.rickmorty.model.RMLocationResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RickMortyLocationAPIService {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RickMortyAPI::class.java)

    fun getRMLocations(): Single<List<RMLocationResponseModel>>{
        return api.getRMLocations()
    }
}