package com.bishwajeet.traveldiaries.data.remote

import com.bishwajeet.traveldiaries.data.model.ReviewsResponse
import com.bishwajeet.traveldiaries.util.BASE_URL
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface IAPIServiceContract {

    @GET("/formData")//"/travelers-api-forwarding/travelers/activities/{activity_id}/reviews")
    fun getReviews(//@Path(value = "activity_id") activity_id: String,
                   //@Query(value = "limit") limit: String,
                   //@Query(value = "offset") offset: String
    ): Call<ReviewsResponse>


    companion object Factory {
        fun create(): IAPIServiceContract {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(IAPIServiceContract::class.java)
        }
    }
}