package com.example.baseforall.nyc.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baseforall.R
import com.example.baseforall.common.extensions.showToast
import com.example.baseforall.common.helpers.launchAndRepeatWithViewLifecycle
import com.example.baseforall.common.navigation.ScreenNavigator
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.baseforall.nyc.presentation.adapter.SchoolListAdapter
import com.example.baseforall.nyc.presentation.viewmodels.SchoolListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NycSchoolListFragment : Fragment(R.layout.fragment_school_list), SchoolListAdapter.SchoolInteractor{

    @Inject
    lateinit var screenNavigator: ScreenNavigator
    val viewModel: SchoolListViewModel by viewModels()
     val schoolListAdapter: SchoolListAdapter by lazy { SchoolListAdapter(this) }
    private lateinit var rvSchool: RecyclerView
    private lateinit var progressBar: ProgressBar


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
        setUpRv(view)
        setUpObservers()
        viewModel.getSchoolList()
    }

    private fun setUpRv(view: View) {
        progressBar = view.findViewById(R.id.progressBar)
        rvSchool = view.findViewById(R.id.rvSchool)
        rvSchool.apply {
            adapter = schoolListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    fun setUpObservers(){
        launchAndRepeatWithViewLifecycle {
            launch {
                renderSchoolList()
            }

            launch {
                renderLoader()
            }

            launch {
                renderErrorMessage()
            }
        }
    }

    private suspend fun renderLoader() {
        viewModel.store.stateFlow.map {
            it.showLoader
        }.collect {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
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

    private suspend fun renderSchoolList() {
        viewModel.store.stateFlow.map {
            it.schoolsList
        }.distinctUntilChanged().collect {
            Log.d("cnrd", it.toString())
            schoolListAdapter.submitList(it)
        }
    }

    override fun onNavigate(lat: String?, long: String?) {
        val gmmIntentUri =
            Uri.parse("google.navigation:q=" + lat.toString() + "," + long.toString() + "&mode=b")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    override fun onPhoneButtonClicked(phone: String?) {
        phone?.let {
            val intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel: " + it)
            startActivity(intent)
        }
    }

    override fun schoolClicked(item: SchoolListPlainResponse) {
        screenNavigator.openSchoolDetail(item.dbn!!,item.school_name!!)
    }


}