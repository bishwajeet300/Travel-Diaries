package com.bishwajeet.traveldiaries.view.review

import javax.inject.Inject

class ReviewPresenter @Inject constructor(private val reviewActivityView: IReviewContract.IReviewListView) : IReviewContract.IReviewListPresenter {


    override fun onLoad() {
        reviewActivityView.onLoadComplete()
    }
}