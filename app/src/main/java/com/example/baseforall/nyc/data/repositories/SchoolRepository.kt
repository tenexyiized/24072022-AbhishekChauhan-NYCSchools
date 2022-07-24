package com.example.baseforall.nyc.data.repositories

import com.example.baseforall.common.result.Karma
import com.example.baseforall.common.utils.SafeRequestExecutor
import com.example.baseforall.nyc.data.SchoolService
import com.example.baseforall.nyc.data.mappers.SchoolDetailRawAndPlainMapper
import com.example.baseforall.nyc.data.mappers.SchoolListRawAndPlainMapper
import com.example.baseforall.nyc.data.models.SchoolDetailPlainResponse
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import java.lang.RuntimeException

class SchoolRepository(val schoolService: SchoolService, val safeRequestExecutor: SafeRequestExecutor,
                       val schoolDetailRawAndPlainMapper: SchoolDetailRawAndPlainMapper,
                       val schoolListRawAndPlainMapper: SchoolListRawAndPlainMapper
                       ){

    suspend  fun getSchoolList():List<SchoolListPlainResponse> =

        safeRequestExecutor.executeRequest {
            schoolService.getSchoolList()
        }?.map {
            schoolListRawAndPlainMapper.toSchoolListPlain(it)
        }


        suspend fun getSchoolDetails(dbn:String): List<SchoolDetailPlainResponse> =

            safeRequestExecutor.executeRequest {
                schoolService.getSchoolDetails(dbn)
            }?.map {
                schoolDetailRawAndPlainMapper.toSchoolDetailPlain(it)
            }

    }


