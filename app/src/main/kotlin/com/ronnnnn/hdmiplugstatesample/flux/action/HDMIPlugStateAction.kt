package com.ronnnnn.hdmiplugstatesample.flux.action

import com.ronnnnn.hdmiplugstatesample.model.HDMIPlugState

sealed class HDMIPlugStateAction<out T>(override val type: String) : Action<T> {

    class HDMIPlugStateChanged(
            override val data: HDMIPlugState
    ) : HDMIPlugStateAction<HDMIPlugState>(TYPE) {

        companion object {
            const val TYPE = "HDMIPlugStateAction.HDMIPlugStateChanged"
        }
    }
}