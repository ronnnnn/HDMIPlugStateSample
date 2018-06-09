package com.ronnnnn.hdmiplugstatesample.receiver.hdmi

import com.ronnnnn.hdmiplugstatesample.di.scope.ReceiverScope
import com.ronnnnn.hdmiplugstatesample.flux.Dispatcher
import com.ronnnnn.hdmiplugstatesample.flux.action.HDMIPlugStateAction
import com.ronnnnn.hdmiplugstatesample.model.HDMIPlugState
import javax.inject.Inject

@ReceiverScope
class HDMIPlugStateBroadcastReceiverActionCreator @Inject constructor(
        private val dispatcher: Dispatcher
) {

    fun onHDMIUnplugged() {

        dispatcher.dispatch(HDMIPlugStateAction.HDMIPlugStateChanged(HDMIPlugState.UNPLUGGED()))
    }

    fun onHDMIPluggedIn() {

        dispatcher.dispatch(HDMIPlugStateAction.HDMIPlugStateChanged(HDMIPlugState.PLUGGED_IN()))
    }
}