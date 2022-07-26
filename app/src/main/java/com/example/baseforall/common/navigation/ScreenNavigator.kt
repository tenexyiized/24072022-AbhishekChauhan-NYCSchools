package com.example.baseforall.common.navigation

interface ScreenNavigator {

    fun openBlankFragment()

    fun openBlankFragment2()

    fun openBlankFragment3()

    fun clearBackStackAndOpenBlankFragment4()

    fun openBlankFragment5()

    fun handleOnBackPressedInFragment():Boolean

    fun goBackFromFragment5ToFragment2()

    fun openSchoolList()

    fun openSchoolDetail(dbn:String,name:String)

}