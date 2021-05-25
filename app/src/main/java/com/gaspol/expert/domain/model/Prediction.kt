package com.gaspol.expert.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Prediction (
    val year: List<String>,
    val quantityPredict: List<Int>,
    val quantity: List<Int>,
    val yearPredict: List<String>
): Parcelable