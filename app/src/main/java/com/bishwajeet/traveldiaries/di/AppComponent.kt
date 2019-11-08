package com.bishwajeet.traveldiaries.di

import com.bishwajeet.traveldiaries.TravelDiariesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    AppModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: TravelDiariesApp)


    override fun inject(instance: DaggerApplication)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TravelDiariesApp): Builder


        fun build(): AppComponent
    }
}