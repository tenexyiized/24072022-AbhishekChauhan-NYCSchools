package com.example.baseforall.nyc.data

import com.example.baseforall.nyc.data.models.SchoolDetailRawResponse
import com.example.baseforall.nyc.data.models.SchoolListRawResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {

    @GET("s3k6-pzi2.json")
    suspend fun getSchoolList(): SchoolListRawResponse

    @GET("f9bf-2cp4.json")
    suspend fun getSchoolDetails(@Query("dbn") dbn: String): SchoolDetailRawResponse

}