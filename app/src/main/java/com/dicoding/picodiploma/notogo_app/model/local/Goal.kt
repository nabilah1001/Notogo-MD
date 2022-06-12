package com.dicoding.picodiploma.notogo_app.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Goal(
    val title: String,
    val location_id: Int,
    val location_name: String,
    val budget: String,
    val date: String,
    val note: String

) : Parcelable