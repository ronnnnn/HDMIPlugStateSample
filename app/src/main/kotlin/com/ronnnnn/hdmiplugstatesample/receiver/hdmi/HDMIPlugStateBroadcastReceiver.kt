package com.ronnnnn.hdmiplugstatesample.receiver.hdmi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class HDMIPlugStateBroadcastReceiver : BroadcastReceiver() {

    companion object {
        private const val PLUGGED_IN: Int = 1
        private const val UNPLUGGED: Int = 0
    }

    @Inject
    lateinit var actionCreator: HDMIPlugStateBroadcastReceiverActionCreator

    override fun onReceive(context: Context?, intent: Intent?) {

        context ?: return
        AndroidInjection.inject(this, context)

        intent ?: return
        if (intent.action == AudioManager.ACTION_HDMI_AUDIO_PLUG) {
            val plugState = intent.getIntExtra(AudioManager.EXTRA_AUDIO_PLUG_STATE, -1)
            val address = intent.getStringExtra("address")
            val state = intent.getIntExtra("state", -1)
            val portName = intent.getStringExtra("portName")

            when (plugState) {
                UNPLUGGED -> {
                    Timber.d(StringBuilder(" \n")
                            .append("---------------  HDMI is unplugged  ---------------\n")
                            .append("address is ${address}\n")
                            .append("state is ${state}\n")
                            .append("port name is ${portName}\n")
                            .append("---------------------------------------------------")
                            .toString()
                    )
                    actionCreator.onHDMIUnplugged()
                }

                PLUGGED_IN -> {
                    Timber.d(StringBuilder(" \n")
                            .append("---------------  HDMI is plugged in ---------------\n")
                            .append("address is ${address}\n")
                            .append("state is ${state}\n")
                            .append("port name is ${portName}\n")
                            .append("---------------------------------------------------")
                            .toString()
                    )
                    actionCreator.onHDMIPluggedIn()
                }

                else -> Timber.e("invalid ACTION_HDMI_AUDIO_PLUG value")
            }
        }
    }
}