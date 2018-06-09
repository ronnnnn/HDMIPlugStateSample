package com.ronnnnn.hdmiplugstatesample.di.module

import com.ronnnnn.hdmiplugstatesample.di.scope.ActivityScope
import com.ronnnnn.hdmiplugstatesample.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}