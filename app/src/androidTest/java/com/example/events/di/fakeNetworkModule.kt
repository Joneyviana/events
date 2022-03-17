package com.example.events.di

import com.example.events.data.FakeEventApi
import com.example.events.data.network.EventsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [NetworkModule::class]
)
@Module
class fakeNetworkModule {

    @Provides
    fun provideEventApi(): EventsApi {
        return FakeEventApi()
    }
}