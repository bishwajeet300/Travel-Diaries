package com.bishwajeet.traveldiaries.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ReviewsResponse (
    @Json(name = "reviews")
    var reviews: List<ReviewsResponse.Review> = listOf(),
    @Json(name = "totalCount")
    var totalCount: Int = 0,
    @Json(name = "averageRating")
    var averageRating: Double = 0.0,
    @Json(name = "pagination")
    var pagination: ReviewsResponse.Pagination = Pagination()
) {
    @JsonClass(generateAdapter = true)
    data class Review(
        @Json(name = "id")
        var id: Int = 0,
        @Json(name = "author")
        var author: Author = Author(),
        @Json(name = "title")
        var title: String = "",
        @Json(name = "message")
        var message: String = "",
        @Json(name = "enjoyment")
        var enjoyment: String = "",
        @Json(name = "isAnonymous")
        var isAnonymous: Boolean = false,
        @Json(name = "rating")
        var rating: Int = 0,
        @Json(name = "created")
        var created: String = "",
        @Json(name = "language")
        var language: String = "",
        @Json(name = "travelerType")
        var travelerType: String = ""
    ) {
        @JsonClass(generateAdapter = true)
        data class Author(
            @Json(name = "fullName")
            var fullName: String = "",
            @Json(name = "country")
            var country: String = ""
        )
    }

    @JsonClass(generateAdapter = true)
    data class Pagination(
        @Json(name = "limit")
        var limit: Int = 0,
        @Json(name = "offset")
        var offset: Int = 0
    )
}