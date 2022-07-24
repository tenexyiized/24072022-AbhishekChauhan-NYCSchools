package com.example.baseforall.nyc.presentation.viewstates

import com.example.baseforall.nyc.data.models.SchoolDetailPlainResponse
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.common.helpers.OneShotEvent

data class SchoolDetailViewState(
    val schoolSatScore: List<SchoolDetailPlainResponse> = emptyList(),
    val error: OneShotEvent<String>? = null,
    val showLoader:Boolean = false
)