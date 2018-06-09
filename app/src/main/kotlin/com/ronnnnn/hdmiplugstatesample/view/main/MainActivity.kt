package com.ronnnnn.hdmiplugstatesample.view.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.ronnnnn.hdmiplugstatesample.R
import com.ronnnnn.hdmiplugstatesample.databinding.ActivityMainBinding
import com.ronnnnn.hdmiplugstatesample.model.HDMIPlugState
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : FragmentActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var store: MainStore

    private lateinit var binding: ActivityMainBinding

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        store.hdmiPlugState
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ hdmiPlugState ->
                    switchHDMIPlugStateImage(hdmiPlugState)
                }, Timber::e)
                .addTo(compositeDisposable)
    }

    override fun onDestroy() {

        compositeDisposable.clear()

        super.onDestroy()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = androidInjector

    private fun switchHDMIPlugStateImage(hdmiPlugState: HDMIPlugState) {

        val imageRes = when (hdmiPlugState) {
            is HDMIPlugState.UNPLUGGED -> R.drawable.power_plug_off
            is HDMIPlugState.PLUGGED_IN -> R.drawable.power_plug
        }

        binding.hdmiPlugStateImageView.setImageResource(imageRes)
    }
}