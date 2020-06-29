package io.github.fuadreza.pikul_dagger.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.fuadreza.pikul_dagger.PikulApp
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.repository.user.UserManager
import io.github.fuadreza.pikul_dagger.ui.login.LoginActivity
import io.github.fuadreza.pikul_dagger.ui.main.profile.ProfileFragment
import io.github.fuadreza.pikul_dagger.ui.main.tes.TesFragment
import io.github.fuadreza.pikul_dagger.ui.main.univ.UnivFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //@Inject
    //lateinit var userManager: UserManager

    //@Inject
    //lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        //(application as PikulApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val userManager = (application as PikulApp).appComponent.userManager()

        if (!userManager.isUserLoggedIn()) {
            Log.d("STATUS LOGIN", "NO LOGIN")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Log.d("STATUS LOGIN", "SUDAH LOGIN")
            setContentView(R.layout.activity_main)

            userManager.userComponent!!.inject(this)
            setupViews()
        }
    }

    private fun setupViews() {
        //welcome.text = userManager.user?.displayName.toString()
        replaceFragment(TesFragment())
        navigation_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.tes -> {
                replaceFragment(TesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.universitas -> {
                replaceFragment(UnivFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                replaceFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.frameContainer, fragment)
            addToBackStack(null)
        }.commit()
    }
}