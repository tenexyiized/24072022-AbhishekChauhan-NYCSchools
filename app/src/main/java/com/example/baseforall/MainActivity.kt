package com.example.baseforall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.baseforall.common.navigation.FragmentContainerWrapper
import com.example.baseforall.common.navigation.ScreenNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentContainerWrapper {

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        if (savedInstanceState == null) {
            screenNavigator.openSchoolList()
        }

    }

    override fun getFragmentContainer(): ViewGroup {
        return findViewById(R.id.fragment_container_view)
    }

}