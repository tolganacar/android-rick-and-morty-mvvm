package com.tolganacar.rickmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.rickmorty.model.Character
import com.tolganacar.rickmorty.model.RMCharacterResponseModel
import com.tolganacar.rickmorty.service.RickMortyAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel: ViewModel() {
    val characterList = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loadingBar = MutableLiveData<Boolean>()

    private val rickMortyApiService = RickMortyAPIService()
    private val disposable = CompositeDisposable()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        loadingBar.value = true

        disposable.add(
            rickMortyApiService.getCharacterList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<RMCharacterResponseModel>(){
                    override fun onSuccess(response: RMCharacterResponseModel) {
                        characterList.value = response.results
                        errorMessage.value = false
                        loadingBar.value = false
                    }

                    override fun onError(e: Throwable) {
                        loadingBar.value = false
                        errorMessage.value = true
                        e.printStackTrace()
                    }
                }
            )
        )
    }
}
