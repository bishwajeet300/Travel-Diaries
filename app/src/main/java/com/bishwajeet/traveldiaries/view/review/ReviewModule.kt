package com.bishwajeet.traveldiaries.view.review

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ReviewModule {

    @Binds
    abstract fun provideReviewListActivityView(activity: ReviewActivity): IReviewContract.IReviewListView

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideReviewListActivityPresenter(reviewActivityView: IReviewContract.IReviewListView): IReviewContract.IReviewListPresenter = ReviewPresenter(reviewActivityView)
    }
}