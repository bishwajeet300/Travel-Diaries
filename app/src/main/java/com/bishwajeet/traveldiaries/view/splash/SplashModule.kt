package com.bishwajeet.traveldiaries.view.splash

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SplashModule {

    @Binds
    abstract fun provideSplashActivityView(activity: SplashActivity): ISplashContract.ISplashView

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideSplashActivityPresenter(splashActivityView: ISplashContract.ISplashView): ISplashContract.ISplashPresenter = SplashPresenter(splashActivityView)
    }
}