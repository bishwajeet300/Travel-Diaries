package com.bishwajeet.traveldiaries.util

import android.os.AsyncTask
import android.util.Log
import android.util.SparseArray
import com.bishwajeet.traveldiaries.data.model.ReviewsResponse
import com.bishwajeet.traveldiaries.data.remote.IAPIServiceContract
import com.bishwajeet.traveldiaries.interfaces.IAsyncListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewsAsyncTask constructor(
    asyncListener: IAsyncListener,
    apiServices: IAPIServiceContract
) : AsyncTask<SparseArray<Any>, Void, ReviewsResponse>() {

    private var mApiServices: IAPIServiceContract = apiServices
    private var mAsyncListener: IAsyncListener = asyncListener


    override fun onPostExecute(result: ReviewsResponse?) {
        super.onPostExecute(result)
        if (result != null && !result.reviews.isNullOrEmpty()) {
            mAsyncListener.onFetchComplete(result)
        }
    }


    override fun onCancelled() {
        super.onCancelled()
        mAsyncListener.onFetchCancelled()
    }


    override fun onPreExecute() {
        super.onPreExecute()
        Log.d("ReviewsAsyncTask", "executing async task")
    }


    override fun doInBackground(vararg params: SparseArray<Any>): ReviewsResponse {
        val call = mApiServices.getReviews()
//            params[0][0].toString(),
//            params[0][1].toString(),
//            params[0][2].toString()
//        )
        var reviewListResponse = ReviewsResponse()
        call.enqueue(object : Callback<ReviewsResponse> {
            override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
                System.out.println(t.message)
                onCancelled()
            }

            override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
                System.out.println(response.body())
                if (response.isSuccessful) {
                    reviewListResponse = response.body()!!
                    onPostExecute(reviewListResponse)
                }
            }
        })
        return reviewListResponse
    }
}