package com.example.baseforall.common.di

import android.app.Activity
import com.example.baseforall.common.permissions.PermissionsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityLevelModule {

    @Provides
    @ActivityScoped
    fun getPermissionHelper(activity:Activity) = PermissionsHelper(activity)

}