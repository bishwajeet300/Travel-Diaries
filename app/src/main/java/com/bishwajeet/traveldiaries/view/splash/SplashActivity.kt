package com.bishwajeet.traveldiaries.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.bishwajeet.traveldiaries.R
import com.bishwajeet.traveldiaries.view.profile.ProfileActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(), ISplashContract.ISplashView {

    @Inject
    lateinit var presenter: ISplashContract.ISplashPresenter
    private lateinit var tvAppName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)
        initializeView()
    }


    private fun initializeView() {
        tvAppName = findViewById(R.id.tvAppName)
        tvAppName.text = getString(R.string.app_name)
        presenter.init()
    }


    override fun onLoadComplete() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, ProfileActivity::class.java))
            finish()
        }, 2000)
    }
}
