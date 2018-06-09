package com.ronnnnn.hdmiplugstatesample.view.main

import com.ronnnnn.hdmiplugstatesample.di.scope.ActivityScope
import com.ronnnnn.hdmiplugstatesample.flux.Dispatcher
import com.ronnnnn.hdmiplugstatesample.flux.action.HDMIPlugStateAction
import com.ronnnnn.hdmiplugstatesample.model.HDMIPlugState
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class MainStore @Inject constructor(dispatcher: Dispatcher) {

    private val hdmiPlugStateBehaviorProcessor: BehaviorProcessor<HDMIPlugState> =
            BehaviorProcessor.createDefault(HDMIPlugState.PLUGGED_IN())

    val hdmiPlugState: Flowable<HDMIPlugState> = hdmiPlugStateBehaviorProcessor

    init {

        dispatcher.on(HDMIPlugStateAction.HDMIPlugStateChanged.TYPE)
                .subscribeOn(Schedulers.io())
                .map { (it as HDMIPlugStateAction.HDMIPlugStateChanged).data }
                .subscribe(hdmiPlugStateBehaviorProcessor)
    }
}