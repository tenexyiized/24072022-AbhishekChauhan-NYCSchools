package com.example.baseforall.nyc.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.baseforall.R
import com.example.baseforall.common.extensions.showToast
import com.example.baseforall.common.helpers.launchAndRepeatWithViewLifecycle
import com.example.baseforall.common.navigation.ScreenNavigator
import com.example.baseforall.nyc.presentation.viewmodels.SchoolDetailViewModel
import com.example.baseforall.nyc.presentation.viewmodels.SchoolListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import javax.inject.Inject

@AndroidEntryPoint
class NycSchoolDetailFragment : Fragment(R.layout.fragment_school_detail){

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    lateinit var satScore:TextView

    val viewModel: SchoolDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    private fun handleBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!screenNavigator.handleOnBackPressedInFragment()) {
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbn = requireArguments().getString("dbn")
        val name = requireArguments().getString("name")
        view.findViewById<TextView>(R.id.tvSchoolName).text = name
        satScore = view.findViewById<TextView>(R.id.textView3)
        viewModel.getSchoolDetail(dbn!!)
        setUpObserver()
    }

    fun setUpObserver(){
        launchAndRepeatWithViewLifecycle {
            launch {
                renderSatScore()
            }

            launch {
                renderErrorMessage()
            }
        }
    }

    private suspend fun renderSatScore() {
        viewModel.store.stateFlow.map {
            it.schoolSatScore
        }.collect {
            Log.d("cnrt","dere " + it.toString())
            if(!it.isEmpty()){
                satScore.text = it[0].satMathAvgScore
            }

        }
    }

    private suspend fun renderErrorMessage() {
        viewModel.store.stateFlow.map {
            it.error
        }.collect {
            it?.let {
                if (!it.hasBeenHandled) {
                    val errorString = it.getContentIfNotHandled()
                    errorString?.let {
                        showToast(errorString)
                    }
                }
            }

        }
    }

}