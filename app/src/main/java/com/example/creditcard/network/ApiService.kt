package com.example.creditcard.network

import com.example.creditcard.model.Card
import com.example.creditcard.model.Responses
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("cards")
    fun getAllCards(): Call<ArrayList<Responses>>

    @POST("cards")
    fun createCard(@Body card: Card): Call<Responses>

}