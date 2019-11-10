package com.bishwajeet.traveldiaries.view.review

interface IReviewContract {

    interface IReviewListView {
        fun onLoadComplete()
    }

    interface IReviewListPresenter {
        fun onLoad()
    }
}