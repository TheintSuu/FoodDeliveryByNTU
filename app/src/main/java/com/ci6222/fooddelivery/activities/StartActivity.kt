package com.ci6222.fooddelivery.activities

import android.os.Bundle
import com.ci6222.fooddelivery.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity  : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        setUpListener()
    }

    private fun setUpListener(){
        btnGetStart.setOnClickListener {
            startActivity(LoginActivity.newIntent(this))
        }
    }
}