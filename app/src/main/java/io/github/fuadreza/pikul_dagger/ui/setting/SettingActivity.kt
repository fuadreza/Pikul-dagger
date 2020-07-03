package io.github.fuadreza.pikul_dagger.ui.setting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.fuadreza.pikul_dagger.PikulApp
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.ui.main.HomeActivity
import kotlinx.android.synthetic.main.activity_setting.*
import javax.inject.Inject

/**
 * Dibuat dengan kerjakerasbagaiquda oleh Shifu pada tanggal 29/06/2020.
 *
 */

class SettingActivity : AppCompatActivity() {

    @Inject
    lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as PikulApp).appComponent.settingComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        setupViews()
    }

    fun setupViews(){
        supportActionBar?.title = "Setting"
        btn_logout.setOnClickListener {
            settingViewModel.logout()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}