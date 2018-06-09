package com.ronnnnn.hdmiplugstatesample.flux.action

interface Action<out T> {
    val type: String
    val data: T
}