package com.dicoding.picodiploma.notogo_app.model.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val token: String,
    val isLogin: Boolean
) : Parcelable