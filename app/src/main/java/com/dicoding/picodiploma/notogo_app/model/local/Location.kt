package com.dicoding.picodiploma.notogo_app.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    var locationId: String? = null,
    var location: String? = null
) : Parcelable