package com.gaspol.expert.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaspol.expert.data.source.remote.CommodityEntity
import com.gaspol.expert.data.source.remote.response.CountryResponse
import com.gaspol.expert.utils.DataDummy

class DetailViewModel : ViewModel() {

    var country = MutableLiveData<CountryResponse>()

    fun getCommodities(): List<CommodityEntity> = DataDummy.generateDummyCommodity()

    fun getCountry(): LiveData<CountryResponse> {
        return country
    }
}