package com.bishwajeet.traveldiaries.view.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.set
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bishwajeet.traveldiaries.data.model.ReviewModel
import com.bishwajeet.traveldiaries.data.model.ReviewsResponse
import com.bishwajeet.traveldiaries.data.remote.IAPIServiceContract
import com.bishwajeet.traveldiaries.interfaces.IAdapterClickListener
import com.bishwajeet.traveldiaries.interfaces.IAsyncListener
import com.bishwajeet.traveldiaries.util.INTENT_REVIEW_MODEL
import com.bishwajeet.traveldiaries.util.ReviewsAsyncTask
import com.bishwajeet.traveldiaries.view.review.ReviewActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class ProfileActivity : DaggerAppCompatActivity(), IProfileContract.IProfileView,
    IAdapterClickListener {

    @Inject
    lateinit var presenter: IProfileContract.IProfilePresenter
    private var reviewsAdapter: ReviewRecyclerAdapter? = null

    private lateinit var clParentLayout: ConstraintLayout
    private lateinit var rvReviews: RecyclerView

    var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(com.bishwajeet.traveldiaries.R.layout.activity_profile)
        initializeView()
    }


    private fun initializeView() {
        clParentLayout = findViewById(com.bishwajeet.traveldiaries.R.id.clParentLayout)
        rvReviews = findViewById(com.bishwajeet.traveldiaries.R.id.rvReviews)

        rvReviews.layoutManager = LinearLayoutManager(this@ProfileActivity)
        rvReviews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                Log.d(
                    "onScrolled",
                    linearLayoutManager.findLastCompletelyVisibleItemPosition().toString().plus(", ").plus(
                        reviewsAdapter?.itemCount
                    )
                )
                if (!isLoading) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == (reviewsAdapter?.itemCount
                            ?: 20) - 6
                    ) {
                        presenter.loadMore(reviewsAdapter?.itemCount ?: 20)
                        isLoading = true
                    }
                }
            }
        })

        //Notify Presenter
        presenter.init()
    }


    override fun callAsyncTaskForReviews(
        asyncListener: IAsyncListener,
        apiService: IAPIServiceContract,
        activityId: String,
        limit: Int,
        offset: Int
    ) {
        val arr: SparseArray<Any> = SparseArray(3)
        arr[0] = activityId
        arr[1] = limit
        arr[2] = offset

        val task = ReviewsAsyncTask(asyncListener, apiService)
        task.execute(arr)
    }


    override fun notifyAdapterOfNewData(reviewListResponse: ReviewsResponse) {
        if (null != reviewsAdapter) {
            reviewsAdapter?.notifyOfDataSetChange(reviewListResponse.reviews)
            isLoading = false
        } else {
            reviewsAdapter = ReviewRecyclerAdapter(this@ProfileActivity, reviewListResponse)
            rvReviews.adapter = reviewsAdapter
            reviewsAdapter?.notifyOfDataSetChange(reviewListResponse.reviews)
        }
    }


    override fun showMessage(s: String) {
        Snackbar.make(clParentLayout, s, Snackbar.LENGTH_LONG).show()
    }


    override fun onBackClick() {
        onBackPressed()
    }


    override fun onShareClick() {
        showMessage("To be implemented")
    }


    override fun onLikeClick(isLiked: Boolean) {
        //TODO:Update
    }


    override fun onReviewClick(review: ReviewsResponse.Review) {
        val reviewModel: ReviewModel = presenter.transformObject(review)

        intent = Intent(this@ProfileActivity, ReviewActivity::class.java)
        intent.putExtra(INTENT_REVIEW_MODEL, reviewModel)
        startActivity(intent)

    }
}