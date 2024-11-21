package com.inforcap.exampleapirest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiService {

    @GET("/posts")
    fun getAllPost(): Call<ArrayList<PostEntity>>

    @GET("/auth/me")
    suspend fun getAll( @Header("Authorization") token: String): ResponseBody


}