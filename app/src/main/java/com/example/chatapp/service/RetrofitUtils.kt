package com.example.chatapp.service

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {
    const val BASE_API = "http://192.168.1.184:9000"

    @JvmStatic
    fun <T> createRetrofit(baseLink: String, clazz: Class<T>): T {
        val ok = OkHttpClient.Builder().callTimeout(
            2, TimeUnit.MINUTES
        ).connectTimeout(
            2, TimeUnit.MINUTES
        ).build()
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create()
        return Retrofit.Builder()
            .baseUrl(baseLink)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(ok)
            .build()
            .create(clazz)
    }
}