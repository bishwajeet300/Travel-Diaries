package com.bishwajeet.traveldiaries.view.splash

interface ISplashContract {

    interface ISplashView {
        fun onLoadComplete()
    }

    interface ISplashPresenter {
        fun init()
    }
}