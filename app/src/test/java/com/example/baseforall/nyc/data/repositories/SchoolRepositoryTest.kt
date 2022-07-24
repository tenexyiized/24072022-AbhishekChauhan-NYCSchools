package com.example.baseforall.nyc.data.repositories

import com.example.baseforall.common.result.data
import com.example.baseforall.common.utils.SafeRequestExecutorImpl
import com.example.baseforall.nyc.data.SchoolService
import com.example.baseforall.nyc.data.mappers.SchoolDetailRawAndPlainMapper
import com.example.baseforall.nyc.data.mappers.SchoolListRawAndPlainMapper
import com.google.common.truth.Truth
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchoolRepositoryTest {

    lateinit var SUT:SchoolRepository

    @Before
    fun setUp() {
        SUT = SchoolRepository(Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/resource/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SchoolService::class.java), SafeRequestExecutorImpl(),
            SchoolDetailRawAndPlainMapper(),
            SchoolListRawAndPlainMapper()

        )
    }

    @Test
    fun schoolListApiTest() = runTest {
        var r = SUT.getSchoolList()
        println(r?.size)
        Truth.assertThat(r).isNotNull()
    }

    @Test
    fun schoolDetailApiTest() = runTest {
        var r = SUT.getSchoolDetails("21K728")
        println(r?.size)
        Truth.assertThat(r).isNotNull()
    }

    @After
    fun tearDown() {
    }
}