package com.example.baseforall.nyc.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseforall.common.result.Karma
import com.example.baseforall.common.store.Store
import com.example.baseforall.nyc.data.models.SchoolDetailPlainResponse
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.baseforall.nyc.entities.SchoolDetailsUseCase
import com.example.baseforall.nyc.entities.SchoolListUseCase
import com.example.baseforall.nyc.presentation.viewstates.SchoolDetailViewState
import com.example.baseforall.nyc.presentation.viewstates.SchoolListViewState
import com.example.common.helpers.OneShotEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailViewModel
@Inject
constructor(
    val schoolDetailUseCase: SchoolDetailsUseCase
) : ViewModel(){

    val store = Store(SchoolDetailViewState())

    fun getSchoolDetail(dbn:String) = viewModelScope.launch {



        val karma = schoolDetailUseCase.invoke(dbn)

        if(karma is Karma.Success) {
            handleSuccessCase(karma)
        }
        else if(karma is Karma.Error) {
            handleFailureCase(karma)
        }

    }

    private suspend fun handleFailureCase(karma: Karma.Error) {
        store.update { viewState ->
            return@update viewState.copy(
                error = OneShotEvent(karma.error.toString()),
                showLoader = false
            )
        }
    }

    private suspend fun handleSuccessCase(karma: Karma.Success<List<SchoolDetailPlainResponse>>) {
        store.update { viewState ->
            return@update viewState.copy(
                schoolSatScore = karma.data,
                showLoader = false
            )
        }
    }

}