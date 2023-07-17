package com.example.chatapp.service

import android.accounts.Account
import com.example.chatapp.model.response.AccountResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpApi {
    @POST("/account/signup")
    fun signUp(
        @Query(value = "username") username: String,
        @Query(value = "password") password: String
    ): Observable<AccountResponse>

    @POST("account/checkexist")
    fun checkAccountExist(@Query(value = "username") username: String):io.reactivex.Observable<AccountResponse>


}