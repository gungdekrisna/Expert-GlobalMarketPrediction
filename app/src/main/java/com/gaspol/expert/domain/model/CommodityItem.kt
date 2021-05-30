package com.gaspol.expert.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CommodityItem (
    val updatedAt: String? = null,
    val name: String? = null,
    val createdAt: String? = null,
    val id: Int? = null
): Parcelable