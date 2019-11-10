package com.bishwajeet.traveldiaries.view.splash

import javax.inject.Inject

class SplashPresenter @Inject constructor(splashActivityView: ISplashContract.ISplashView) : ISplashContract.ISplashPresenter {

    private var mSplashActivityVew: ISplashContract.ISplashView = splashActivityView

    override fun init() {
        mSplashActivityVew.onLoadComplete()
    }
}