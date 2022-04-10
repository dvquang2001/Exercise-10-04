package com.midterm.dovanquang.data.api

import com.midterm.dovanquang.data.model.PostAPIResponse
import retrofit2.http.GET
import rx.Observable

interface ApiService {

    @GET("posts")
    fun getPost(): Observable<PostAPIResponse>
}