package com.bishwajeet.traveldiaries.view.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bishwajeet.traveldiaries.R

class ProfileActivity : AppCompatActivity(), IProfileContract.IProfileView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}
