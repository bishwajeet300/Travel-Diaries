package com.bishwajeet.traveldiaries.util

import com.bishwajeet.traveldiaries.data.model.Author
import com.bishwajeet.traveldiaries.data.model.ReviewModel
import com.bishwajeet.traveldiaries.data.model.ReviewsResponse

open class AppUtils {

    fun transformObject(reviewResponse: ReviewsResponse.Review): ReviewModel {

        return ReviewModel(reviewResponse.id,
            Author(reviewResponse.author.fullName, reviewResponse.author.country),
            reviewResponse.title,
            reviewResponse.message,
            reviewResponse.enjoyment,
            reviewResponse.isAnonymous,
            reviewResponse.rating,
            reviewResponse.created,
            reviewResponse.language,
            reviewResponse.travelerType
        )
    }

}