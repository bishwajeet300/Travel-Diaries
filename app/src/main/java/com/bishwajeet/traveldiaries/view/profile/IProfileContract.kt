package com.bishwajeet.traveldiaries.view.profile

import com.bishwajeet.traveldiaries.data.model.ReviewModel
import com.bishwajeet.traveldiaries.data.model.ReviewsResponse
import com.bishwajeet.traveldiaries.data.remote.IAPIServiceContract
import com.bishwajeet.traveldiaries.interfaces.IAsyncListener

interface IProfileContract {

    interface IProfileView {
        fun callAsyncTaskForReviews(
            asyncListener: IAsyncListener,
            apiService: IAPIServiceContract,
            activityId: String,
            limit: Int,
            offset: Int
        )

        fun notifyAdapterOfNewData(reviewListResponse: ReviewsResponse)

        fun showMessage(s: String)

        fun showLoadingLayout()

        fun hideLoadingLayout()
    }


    interface IProfilePresenter {
        fun init()

        fun loadMore(offset: Int)

        fun transformObject(review: ReviewsResponse.Review): ReviewModel
    }
}