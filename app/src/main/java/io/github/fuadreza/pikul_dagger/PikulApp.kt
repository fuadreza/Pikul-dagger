package io.github.fuadreza.pikul_dagger


import android.app.Application
import io.github.fuadreza.pikul_dagger.di.AppComponent
import io.github.fuadreza.pikul_dagger.di.DaggerAppComponent

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 24/06/2020.
 *
 */

open class PikulApp: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}