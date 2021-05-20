package com.gaspol.expert.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Country (
    val name: String,
    val code: String
): Parcelable