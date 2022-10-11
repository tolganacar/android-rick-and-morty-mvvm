package com.tolganacar.rickmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.rickmorty.model.RMCharacter
import com.tolganacar.rickmorty.model.RMCharacterResponseModel
import com.tolganacar.rickmorty.service.RickMortyAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RMCharacterListVM: ViewModel() {
    val characterList = MutableLiveData<List<RMCharacter>>()
    val shouldShowErrorMessage = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    private val rickMortyApiService = RickMortyAPIService()
    private val disposable = CompositeDisposable()


    fun getRMCharacterListFromAPI(){
        isLoading.value = true

        disposable.add(
            rickMortyApiService.getRMCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<RMCharacterResponseModel>(){
                    override fun onSuccess(response: RMCharacterResponseModel) {
                        characterList.value = response.results
                        shouldShowErrorMessage.value = false
                        isLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        shouldShowErrorMessage.value = true
                        e.printStackTrace()
                    }
                }
            )
        )
    }
}
