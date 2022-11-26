package com.tolganacar.rickmorty.viewmodel.rmlocationlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.rickmorty.model.RMLocationResponseModel
import com.tolganacar.rickmorty.service.RickMortyLocationAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RMLocationListViewModel : ViewModel() {
    val locations = MutableLiveData<List<RMLocationResponseModel>>()
    val shouldShowLocationErrorMessage = MutableLiveData<Boolean>()
    val isLoadingLocation = MutableLiveData<Boolean>()

    private val locationAPIService = RickMortyLocationAPIService()
    private val disposable = CompositeDisposable()

    fun getRMLocationListFromAPI() {
        isLoadingLocation.value = true

        disposable.add(
            locationAPIService.getRMLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<RMLocationResponseModel>>() {
                    override fun onSuccess(response: List<RMLocationResponseModel>) {
                        locations.value = response
                        shouldShowLocationErrorMessage.value = false
                        isLoadingLocation.value = false
                    }

                    override fun onError(e: Throwable) {
                        isLoadingLocation.value = false
                        shouldShowLocationErrorMessage.value = true
                        e.printStackTrace()
                    }
                }
                ))
    }
}