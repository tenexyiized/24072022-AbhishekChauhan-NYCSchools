package com.example.baseforall.nyc.presentation.viewstates

import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.common.helpers.OneShotEvent

data class SchoolListViewState (
            val schoolsList: List<SchoolListPlainResponse> = emptyList(),
            val error:OneShotEvent<String>? = null,
            val showLoader:Boolean = false
        )