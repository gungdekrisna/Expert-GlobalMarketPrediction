package com.gaspol.expert.detail

import androidx.lifecycle.ViewModel
import com.gaspol.expert.data.source.remote.CommodityEntity
import com.gaspol.expert.utils.DataDummy

class DetailViewModel : ViewModel() {

    fun getCommodities(): List<CommodityEntity> = DataDummy.generateDummyCommodity()

}