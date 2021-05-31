package com.gaspol.expert.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecentSearch (
    val id: Int = 0,
    var text: String? = null
): Parcelable