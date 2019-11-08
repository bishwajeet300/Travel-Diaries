package com.bishwajeet.traveldiaries.view.review

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ReviewListModule {

    @Binds
    abstract fun provideReviewListActivityView(activity: ReviewListActivity): IReviewListContract.IReviewListView

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideReviewListActivityPresenter(reviewListActivityView: IReviewListContract.IReviewListView): IReviewListContract.IReviewListPresenter = ReviewListPresenter(reviewListActivityView)
    }
}