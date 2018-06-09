package com.ronnnnn.hdmiplugstatesample.model

sealed class HDMIPlugState(val value: Int) {

    class UNPLUGGED : HDMIPlugState(UNPLUGGED) {

        private companion object {

            private const val UNPLUGGED: Int = 0
        }
    }

    class PLUGGED_IN : HDMIPlugState(PLUGGED_IN) {

        private companion object {

            private const val PLUGGED_IN: Int = 1
        }
    }
}