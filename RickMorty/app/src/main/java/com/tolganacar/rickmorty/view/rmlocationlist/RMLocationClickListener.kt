package com.tolganacar.rickmorty.view.rmlocationlist

import com.tolganacar.rickmorty.model.RMLocationResponseModel

interface RMLocationClickListener {
    fun onRMLocationClicked(location: RMLocationResponseModel)
}