package com.example.chatapp.service

import com.example.chatapp.model.response.AccountResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface SignInApi {
    @POST("/account/login")
    fun signIn(@Query(value = "username") username: String,@Query(value = "password") password: String): Observable<AccountResponse>
}