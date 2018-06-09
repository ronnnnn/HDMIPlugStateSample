package com.ronnnnn.hdmiplugstatesample.di.component

import com.ronnnnn.hdmiplugstatesample.App
import com.ronnnnn.hdmiplugstatesample.di.module.ActivityModule
import com.ronnnnn.hdmiplugstatesample.di.module.ReceiverModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    ReceiverModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}