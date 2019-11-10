package com.bishwajeet.traveldiaries.view.profile

import com.bishwajeet.traveldiaries.data.remote.IAPIServiceContract
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ProfileModule {

    @Binds
    abstract fun provideProfileActivityView(activity: ProfileActivity): IProfileContract.IProfileView

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideProfileActivityPresenter(profileActivityView: IProfileContract.IProfileView, apiServices: IAPIServiceContract): IProfileContract.IProfilePresenter = ProfilePresenter(profileActivityView, apiServices)
    }
}