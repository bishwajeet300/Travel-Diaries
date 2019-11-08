package com.bishwajeet.traveldiaries.di

import android.content.Context
import com.bishwajeet.traveldiaries.TravelDiariesApp
import com.bishwajeet.traveldiaries.data.remote.IAPIServiceContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideContext(application: TravelDiariesApp): Context = application.applicationContext


        @JvmStatic
        @Provides
        @Singleton
        fun provideApiServices(): IAPIServiceContract = IAPIServiceContract.create()
    }
}