package com.bishwajeet.traveldiaries.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bishwajeet.traveldiaries.R

class SplashActivity : AppCompatActivity(), ISplashContract.ISplashView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onLoadComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
