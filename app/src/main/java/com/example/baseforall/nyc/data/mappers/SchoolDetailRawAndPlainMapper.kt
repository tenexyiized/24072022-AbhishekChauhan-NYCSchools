package com.example.baseforall.nyc.data.mappers

import com.example.baseforall.nyc.data.models.SchoolDetailPlainResponse
import com.example.baseforall.nyc.data.models.SchoolDetailRawResponse

class SchoolDetailRawAndPlainMapper {

    fun toSchoolDetailPlain(item: SchoolDetailRawResponse.SchoolDetailRawResponseItem) =
        SchoolDetailPlainResponse(
            item.dbn,
            item.numOfSatTestTakers,
            item.satCriticalReadingAvgScore,
            item.satMathAvgScore,
            item.satWritingAvgScore,
            item.schoolName
        )

}