package com.bishwajeet.traveldiaries.view.profile

import com.bishwajeet.traveldiaries.data.model.ReviewModel
import com.bishwajeet.traveldiaries.data.model.ReviewsResponse
import com.bishwajeet.traveldiaries.data.remote.IAPIServiceContract
import com.bishwajeet.traveldiaries.interfaces.IAsyncListener
import com.bishwajeet.traveldiaries.util.AppUtils
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    profileActivityView: IProfileContract.IProfileView,
    apiServices: IAPIServiceContract
) : IProfileContract.IProfilePresenter, IAsyncListener {


    private var mProfileActivityView: IProfileContract.IProfileView = profileActivityView
    private var mApiServices: IAPIServiceContract = apiServices


    override fun init() {
        mProfileActivityView.showLoadingLayout()
        mProfileActivityView.callAsyncTaskForReviews(this as IAsyncListener, mApiServices, "23776", 30, 0)
    }


    override fun loadMore(offset: Int) {
        mProfileActivityView.callAsyncTaskForReviews(this as IAsyncListener, mApiServices, "23776", 30, offset)
    }


    override fun onFetchComplete(reviewListResponse: ReviewsResponse) {
        mProfileActivityView.notifyAdapterOfNewData(reviewListResponse)
        mProfileActivityView.hideLoadingLayout()
    }


    override fun onFetchCancelled() {
        mProfileActivityView.showMessage("That\\'s all we had!")
    }


    override fun transformObject(review: ReviewsResponse.Review): ReviewModel {
        return AppUtils().transformObject(review)
    }
}

