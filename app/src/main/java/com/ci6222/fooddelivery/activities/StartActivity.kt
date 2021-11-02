package com.ci6222.fooddelivery.activities

import android.os.Bundle
import android.util.Log
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity  : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        setUpListener()
    }

    private fun setUpListener(){


        btnGetStart.setOnClickListener {
            if(SessionManager.login_status == false) {
                Log.d("login status", SessionManager.login_status.toString())
                startActivity(LoginActivity.newIntent(this))
                finish()
            }else{
                startActivity(MainActivity.newIntent(this))
                finish()
            }

            //startActivity(LoginActivity.newIntent(this))

        }
    }
}