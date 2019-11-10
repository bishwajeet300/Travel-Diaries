package com.bishwajeet.traveldiaries.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewModel(var id: Int,
                       var author: Author,
                       var title: String,
                       var message: String,
                       var enjoyment: String,
                       var isAnonymous: Boolean,
                       var rating: Int,
                       var created: String,
                       var language: String,
                       var travelerType: String): Parcelable

@Parcelize
data class Author(
    var fullName: String,
    var country: String
) : Parcelable

