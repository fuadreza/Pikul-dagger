package io.github.fuadreza.pikul_dagger.views.setting

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.views.login.LoginActivity
import io.github.fuadreza.pikul_dagger.views.main.HomeActivity
import kotlinx.android.synthetic.main.activity_setting.*
import javax.inject.Inject


/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 29/06/2020.
 *
 */

@AndroidEntryPoint
class SettingActivity : AppCompatActivity(), LifecycleOwner {

    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

//        (application as PikulApp).appComponent.settingComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        lifecycle.addObserver(settingViewModel)

        setupViews()
    }

    fun setupViews() {
        supportActionBar?.title = "Setting"
        btn_logout.setOnClickListener {
            settingViewModel.logout()

            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

            finish()
        }
    }
}