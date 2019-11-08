package com.bishwajeet.traveldiaries.data.model

data class ReviewListResponse(
    var reviews: List<Review> = listOf(),
    var totalCount: Int = 0,
    var averageRating: Double = 0.0,
    var pagination: Pagination = Pagination()
) {
    data class Review(
        var id: Int = 0,
        var author: Author = Author(),
        var title: String = "",
        var message: String = "",
        var enjoyment: String = "",
        var isAnonymous: Boolean = false,
        var rating: Int = 0,
        var created: String = "",
        var language: String = "",
        var travelerType: String = ""
    ) {
        data class Author(
            var fullName: String = "",
            var country: String = ""
        )
    }

    data class Pagination(
        var limit: Int = 0,
        var offset: Int = 0
    )
}