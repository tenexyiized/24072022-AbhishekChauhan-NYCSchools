package com.example.baseforall.nyc.data.mappers

import com.example.baseforall.nyc.data.models.SchoolDetailPlainResponse
import com.example.baseforall.nyc.data.models.SchoolDetailRawResponse
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.baseforall.nyc.data.models.SchoolListRawResponse

class SchoolListRawAndPlainMapper {
    fun toSchoolListPlain(item: SchoolListRawResponse.SchoolResponseListItem) =
        SchoolListPlainResponse(
            item.overview_paragraph,
            item.school_name,
            item.phone_number,
            item.website,
            item.latitude,
            item.longitude,
            item.dbn,
            item.location
        )
}