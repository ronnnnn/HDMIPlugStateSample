package com.ronnnnn.hdmiplugstatesample.di.module

import com.ronnnnn.hdmiplugstatesample.di.scope.ReceiverScope
import com.ronnnnn.hdmiplugstatesample.receiver.hdmi.HDMIPlugStateBroadcastReceiver
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ReceiverModule {

    @ReceiverScope
    @ContributesAndroidInjector
    internal abstract fun contributeHDMIPlugStateBroadcastReceiver(): HDMIPlugStateBroadcastReceiver
}