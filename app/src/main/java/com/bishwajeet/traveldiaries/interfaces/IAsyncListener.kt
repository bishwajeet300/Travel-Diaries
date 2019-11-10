package com.bishwajeet.traveldiaries.interfaces

import com.bishwajeet.traveldiaries.data.model.ReviewsResponse

interface IAsyncListener {
    fun onFetchComplete(reviewListResponse: ReviewsResponse)

    fun onFetchCancelled()
}