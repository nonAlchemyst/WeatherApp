package com.example.weatherapp.di

import com.example.weatherapp.data.api.NetworkService
import com.example.weatherapp.data.geo.GeoRepository
import com.example.weatherapp.data.geo.MyLocationGeocoder
import com.example.weatherapp.data.repositories.GeocodingLocalDataSource
import com.example.weatherapp.data.repositories.GeocodingLocalDataSourceImpl
import com.example.weatherapp.data.repositories.GeocodingRemoteDataSource
import com.example.weatherapp.data.repositories.GeocodingRemoteDataSourceImpl
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityModule::class,
    UseCasesModule::class,
    RepositoriesModule::class
])
interface AppComponent

@Module
class ActivityModule

@Module
interface UseCasesModule

@Module
interface RepositoriesModule {
    @Binds
    fun provideGeocodingLocalDataSource(geocodingLocalDataSourceImpl: GeocodingLocalDataSourceImpl): GeocodingLocalDataSource

    @Binds
    fun provideGeocodingRemoteDataSource(geocodingRemoteDataSourceImpl: GeocodingRemoteDataSourceImpl): GeocodingRemoteDataSource
}