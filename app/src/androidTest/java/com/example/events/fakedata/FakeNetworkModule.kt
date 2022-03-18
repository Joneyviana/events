package com.example.events.fakedata

import com.example.events.data.network.EventsApi
import com.example.events.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
@Module
class FakeNetworkModule {

    @Provides
    @Singleton
    fun provideEventApi(): EventsApi {
        return FakeEventApi()
    }
}