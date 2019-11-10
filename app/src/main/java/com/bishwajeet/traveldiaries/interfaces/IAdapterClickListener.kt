package com.bishwajeet.traveldiaries.interfaces

import com.bishwajeet.traveldiaries.data.model.ReviewsResponse

interface IAdapterClickListener {

    fun onBackClick()

    fun onShareClick()

    fun onLikeClick(isLiked: Boolean)

    fun onReviewClick(review: ReviewsResponse.Review)
}