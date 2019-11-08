package com.bishwajeet.traveldiaries.view.review

interface IReviewListContract {

    interface IReviewListView {
        fun onLoadComplete()
    }

    interface IReviewListPresenter {
        fun onLoad()
    }
}