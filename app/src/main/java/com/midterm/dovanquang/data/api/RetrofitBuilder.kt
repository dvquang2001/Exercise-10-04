package com.midterm.dovanquang.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

object RetrofitBuilder {
    private const val BASE_URL = "https://api.jsonbin.io/"
    private var mOkHttpClient: OkHttpClient? = null
    private var sslContext: SSLContext? = null
    private var sslSocketFactory: javax.net.ssl.SSLSocketFactory? = null

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        sslSocketFactory = sslContext?.socketFactory
        val okHttpClientBuilder = OkHttpClient().newBuilder()
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .addInterceptor{ chain ->
                val original = chain.request()
                val requset = original.newBuilder()
                    .method(original.method(),original.body())
                    .build()
                chain.proceed(requset)
            }

        mOkHttpClient = okHttpClientBuilder.build()

        return mOkHttpClient as OkHttpClient
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}