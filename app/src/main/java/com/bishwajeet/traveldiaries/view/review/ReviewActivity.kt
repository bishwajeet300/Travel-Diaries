package com.bishwajeet.traveldiaries.view.review

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bishwajeet.traveldiaries.R
import com.bishwajeet.traveldiaries.data.model.ReviewModel
import com.bishwajeet.traveldiaries.util.INTENT_REVIEW_MODEL
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ReviewActivity : DaggerAppCompatActivity(), IReviewContract.IReviewListView {

    @Inject
    lateinit var presenter: IReviewContract.IReviewListPresenter

    private lateinit var tvProfile: TextView
    private lateinit var tvName: TextView
    private lateinit var tvTour: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvMessage: TextView
    private lateinit var tvTimeAndPlace: TextView
    private lateinit var ivBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_review)
        initializeUI()
    }


    private fun initializeUI() {
        tvProfile = findViewById(R.id.tvProfile)
        tvName = findViewById(R.id.tvName)
        tvTour = findViewById(R.id.tvTour)
        tvRating = findViewById(R.id.tvRating)
        tvMessage = findViewById(R.id.tvMessage)
        tvTimeAndPlace = findViewById(R.id.tvTimeAndPlace)
        ivBack = findViewById(R.id.ivBack)
        ivBack.setOnClickListener {
            onBackPressed()
        }


        presenter.onLoad()
    }


    override fun onLoadComplete() {
        if (intent.hasExtra(INTENT_REVIEW_MODEL)) {
            val review = intent.getParcelableExtra(INTENT_REVIEW_MODEL) as ReviewModel

            if(review.isAnonymous) {
                tvProfile.text = "A"
                tvName.text = getString(R.string.anonymous)
            } else {
                tvProfile.text = review.author.fullName[0].toString()
                tvName.text = review.author.fullName
            }

            tvTour.text = getString(R.string.tour_name)
            tvRating.text = review.rating.toString()

            if(TextUtils.isEmpty(review.title)) {
                tvMessage.text = review.message
            } else {
                tvMessage.text = review.title
            }

            tvTimeAndPlace.text = review.created.subSequence(0, 10).toString().plus("\n").plus(review.author.country)
        }
    }
}
