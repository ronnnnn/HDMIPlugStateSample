package com.ronnnnn.hdmiplugstatesample

import android.content.IntentFilter
import android.media.AudioManager
import com.ronnnnn.hdmiplugstatesample.di.component.DaggerAppComponent
import com.ronnnnn.hdmiplugstatesample.receiver.hdmi.HDMIPlugStateBroadcastReceiver
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    private val hdmiPlugStateBroadcastReceiver: HDMIPlugStateBroadcastReceiver =
            HDMIPlugStateBroadcastReceiver()

    override fun onCreate() {
        super.onCreate()

        initializeTimber()
        initializeReceiver()
    }

    override fun onTerminate() {
        terminateReceiver()

        super.onTerminate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initializeReceiver() {
        registerReceiver(
                hdmiPlugStateBroadcastReceiver,
                IntentFilter(AudioManager.ACTION_HDMI_AUDIO_PLUG)
        )
    }

    private fun terminateReceiver() {
        unregisterReceiver(hdmiPlugStateBroadcastReceiver)
    }
}