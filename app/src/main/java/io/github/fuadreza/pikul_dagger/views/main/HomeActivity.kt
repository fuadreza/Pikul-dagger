package io.github.fuadreza.pikul_dagger.views.main

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.pikul_dagger.R
import io.github.fuadreza.pikul_dagger.views.main.profile.ProfileFragment
import io.github.fuadreza.pikul_dagger.views.main.tes.TesFragment
import io.github.fuadreza.pikul_dagger.views.main.tes.TesFragmentDirections
import io.github.fuadreza.pikul_dagger.views.main.univ.UnivFragment
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), LifecycleOwner {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var hostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        lifecycle.addObserver(homeViewModel)

        setupViews()

        /*if (!userManager.isUserLoggedIn()) {
            Log.d("STATUS LOGIN", "NO LOGIN")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Log.d("STATUS LOGIN", "SUDAH LOGIN")
            setContentView(R.layout.activity_main)

            //userManager.userComponent!!.inject(this)
            setupViews()
        }*/
    }

    override fun onResume() {
        super.onResume()
        observe()
        NavigationUI.setupWithNavController(
            navigation_menu,
            hostFragment.navController
        );
    }

    private fun observe() {

    }

    private fun setupViews() {
        //welcome.text = userManager.user?.displayName.toString()
//        val host = NavHostFragment.create(R.navigation.nav_graph)
        hostFragment = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction().replace(R.id.frameContainer, hostFragment)
            .setPrimaryNavigationFragment(hostFragment).commit()
//        replaceFragment(TesFragment())
//        navigation_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tesFragment -> {
                    TesFragmentDirections.actionTesFragmentToUnivFragment()
                    replaceFragment(TesFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.univFragment -> {
                    replaceFragment(UnivFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profileFragment -> {
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
        }.commit()
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}