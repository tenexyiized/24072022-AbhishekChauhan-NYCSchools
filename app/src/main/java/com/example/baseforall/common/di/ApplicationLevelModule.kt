package com.example.baseforall.common.di

import android.content.Context
import androidx.room.Room
import com.example.baseforall.common.utils.SafeRequestExecutor
import com.example.baseforall.common.utils.SafeRequestExecutorImpl
import com.example.baseforall.eventbus.CustomEventBus
import com.example.baseforall.nyc.data.SchoolService
import com.example.baseforall.nyc.data.mappers.SchoolDetailRawAndPlainMapper
import com.example.baseforall.nyc.data.mappers.SchoolListRawAndPlainMapper
import com.example.baseforall.nyc.data.repositories.SchoolRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationLevelModule {

    @Singleton
    @Provides
    fun getEventBus() = CustomEventBus()


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://data.cityofnewyork.us/resource/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideSafeExecutor():SafeRequestExecutor = SafeRequestExecutorImpl()

    @Provides
    fun getSchoolDetailRawAndPlainMapper() = SchoolDetailRawAndPlainMapper()

    @Provides
    fun getSchoolListRawAndPlainMapper() = SchoolListRawAndPlainMapper()

    @Provides
    fun provideSchoolRepo(service: SchoolService,
                          executor:SafeRequestExecutor,
                          schoolDetailRawAndPlainMapper:SchoolDetailRawAndPlainMapper,
                          schoolListRawAndPlainMapper:SchoolListRawAndPlainMapper,
    ): SchoolRepository = SchoolRepository(service,executor,schoolDetailRawAndPlainMapper,schoolListRawAndPlainMapper)

    @Provides
    fun provideCharacterService(retrofit: Retrofit): SchoolService = retrofit.create(SchoolService::class.java)

}