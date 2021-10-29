package com.ci6222.fooddelivery.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.fragment.ProfileFragment
import com.ci6222.fooddelivery.fragment.RestartuantFragment
import com.ci6222.fooddelivery.utilities.FRAGMENT_Home
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callFragment(RestartuantFragment.newInstance("a", "b"))
        setUpBottomNavigation()
    }


    fun setUpBottomNavigation(){
        BottomNav.setOnNavigationItemSelectedListener (object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.nav_home -> {
                        callFragment(RestartuantFragment.newInstance("a", "b"))
                        return true
                    }

                    R.id.nav_profile -> {
                        callFragment(ProfileFragment.newInstance("a", "b"))
                        return true
                    }

                }
                return false

            }
        })
    }

    fun callFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction().
            replace(R.id.container, fragment)
            .addToBackStack(FRAGMENT_Home).commit()
    }
}