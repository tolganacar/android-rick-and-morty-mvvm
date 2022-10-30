package com.tolganacar.rickmorty.viewmodel.rmlocationdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.rickmorty.model.RMLocationResponseModel

class RMLocationDetailViewModel : ViewModel() {

    val locationLiveData = MutableLiveData<RMLocationResponseModel>()

    fun setLocation(location: RMLocationResponseModel) {
        locationLiveData.value = location
    }
}