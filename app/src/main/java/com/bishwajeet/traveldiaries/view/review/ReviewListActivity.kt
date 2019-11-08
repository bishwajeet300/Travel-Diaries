package com.bishwajeet.traveldiaries.view.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bishwajeet.traveldiaries.R

class ReviewListActivity : AppCompatActivity(), IReviewListContract.IReviewListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_list)
    }


    override fun onLoadComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
