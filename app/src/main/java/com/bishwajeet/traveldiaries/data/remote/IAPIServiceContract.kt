package com.bishwajeet.traveldiaries.data.remote

import com.bishwajeet.traveldiaries.data.model.ReviewListResponse
import com.bishwajeet.traveldiaries.util.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface IAPIServiceContract {

    @GET("/travelers-api-forwarding/travelers/activities/{activity_id}/reviews?limit={_limit}&offset={_offset}")
    fun getReviews(@Path(value = "activity_id", encoded = true) activity_id: String,
                   @Path(value = "_limit", encoded = true) _limit: Int,
                   @Path(value = "_offset", encoded = true) _offset: Int): ReviewListResponse


    companion object Factory {
        fun create(): IAPIServiceContract {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(IAPIServiceContract::class.java)
        }
    }
}