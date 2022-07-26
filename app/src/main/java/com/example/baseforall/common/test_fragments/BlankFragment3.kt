package com.example.baseforall.common.test_fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.baseforall.R
import com.example.baseforall.common.navigation.ScreenNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class BlankFragment3 : Fragment(R.layout.fragment_blank3) {

    companion object{
        val sag = "BlankFragment3"
    }

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d("lifecycle_sc_f",sag + " " +  "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle_sc_f", sag + " " +  "onCreate")

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("lifecycle_sc_f", sag + " " +  "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("lifecycle_sc_f", sag + " " +  "onViewCreated")
        val someInt = requireArguments().getInt("some_int")

        view.findViewById<TextView>(R.id.tvDes).setOnClickListener {
            screenNavigator.clearBackStackAndOpenBlankFragment4()
        }

        view.findViewById<TextView>(R.id.tvDes2).setOnClickListener {
            screenNavigator.openBlankFragment5()
        }



    }

    override fun onStart() {
        Log.d("lifecycle_sc_f", sag + " " +  "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("lifecycle_sc_f", sag + " " +  "onResume")
        super.onResume()
    }

    override fun onStop() {
        Log.d("lifecycle_sc_f", sag + " " +  "onStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("lifecycle_sc_f", sag + " " +  "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        Log.d("lifecycle_sc_f",sag + " " +  "onDestroyView" )
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("lifecycle_sc_f", sag + " " +  "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("lifecycle_sc_f", sag + " " +  "onDetach")
        super.onDetach()
    }

    override fun toString(): String {
        return sag
    }


}