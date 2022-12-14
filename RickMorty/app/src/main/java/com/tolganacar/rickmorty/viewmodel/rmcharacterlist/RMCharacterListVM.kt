package com.tolganacar.rickmorty.viewmodel.rmcharacterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.rickmorty.model.RMCharacter
import com.tolganacar.rickmorty.model.RMCharacterResponseModel
import com.tolganacar.rickmorty.service.RickMortyAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RMCharacterListVM : ViewModel() {
    val characterList = MutableLiveData<List<RMCharacter>>()
    val shouldShowErrorMessage = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    private val rickMortyApiService = RickMortyAPIService()
    private val disposable = CompositeDisposable()

    private val _filteredCharacterList = MutableLiveData<List<RMCharacter>>()
    val filteredCharacterList: LiveData<List<RMCharacter>> get() = _filteredCharacterList


    fun getRMCharacterListFromAPI() {
        isLoading.value = true

        disposable.add(
            rickMortyApiService.getRMCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RMCharacterResponseModel>() {
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

    fun filterCharacterList(searchText: String) {
        if (searchText.isEmpty()) {
            resetCharacterList()
        } else {
            _filteredCharacterList.value = characterList.value?.filter {
                (it.name.contains(searchText, ignoreCase = true)
                        || it.gender.contains(searchText, ignoreCase = true)
                        || it.status.contains(searchText, ignoreCase = true))
            }
        }
    }

    private fun resetCharacterList() {
        _filteredCharacterList.value = characterList.value
    }

}
