package com.example.tzsushi.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ItemsAPI {
//    @GET("128hUGBg3biXOTmhbvx0xkYS-4sYye8HL/view?usp=sharing")
//        @GET("128hUGBg3biXOTmhbvx0xkYS-4sYye8HL")
@GET("128hUGBg3biXOTmhbvx0xkYS-4sYye8HL")
    fun getItems(): Call<Root?>

    @GET("uc")
    fun getItemsById(
        @Query("id") id: String
    ): Call<Root>
}

