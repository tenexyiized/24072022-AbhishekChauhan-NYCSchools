package com.example.baseforall.nyc.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseforall.common.result.Karma
import com.example.baseforall.common.store.Store
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.baseforall.nyc.entities.SchoolListUseCase
import com.example.baseforall.nyc.presentation.viewstates.SchoolListViewState
import com.example.common.helpers.OneShotEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel
@Inject
constructor(
    val schoolListUseCase: SchoolListUseCase
) : ViewModel(){

    val store = Store(SchoolListViewState())

    fun getSchoolList() = viewModelScope.launch {

        store.update { viewState ->
            return@update viewState.copy(
                showLoader = true
            )
        }

        val karma = schoolListUseCase.invoke(Unit)

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

    private suspend fun handleSuccessCase(karma: Karma.Success<List<SchoolListPlainResponse>>) {
        store.update { viewState ->
            return@update viewState.copy(
                schoolsList = karma.data,
                showLoader = false
            )
        }
    }
}

