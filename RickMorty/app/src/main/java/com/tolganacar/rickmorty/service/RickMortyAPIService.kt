package com.tolganacar.rickmorty.service

import com.tolganacar.rickmorty.model.RMCharacterResponseModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RickMortyAPIService {
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().run {
        addInterceptor(interceptor).build()
    }

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RickMortyAPI::class.java)

    fun getRMCharacters(): Single<RMCharacterResponseModel> {
        return api.getRMCharacters()
    }

    fun getNextRMCharacter(page: String): Single<RMCharacterResponseModel> {
        return api.getNextRMCharacters(page)
    }
}