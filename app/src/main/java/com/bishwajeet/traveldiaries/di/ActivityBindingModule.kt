package com.bishwajeet.traveldiaries.di

import com.bishwajeet.traveldiaries.view.profile.ProfileActivity
import com.bishwajeet.traveldiaries.view.profile.ProfileModule
import com.bishwajeet.traveldiaries.view.review.ReviewActivity
import com.bishwajeet.traveldiaries.view.review.ReviewModule
import com.bishwajeet.traveldiaries.view.splash.SplashActivity
import com.bishwajeet.traveldiaries.view.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivityScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun bindSplashActivity(): SplashActivity


    @PerActivityScope
    @ContributesAndroidInjector(modules = [ReviewModule::class])
    abstract fun bindReviewListActivity(): ReviewActivity


    @PerActivityScope
    @ContributesAndroidInjector(modules = [ProfileModule::class])
    abstract fun bindProfileActivity(): ProfileActivity
}