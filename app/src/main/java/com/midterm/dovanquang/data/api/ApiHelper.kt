package com.midterm.dovanquang.data.api

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.midterm.dovanquang.data.model.PostAPIResponse
import com.midterm.dovanquang.ui.main.view.activity.BaseActivity
import retrofit2.HttpException
import retrofit2.http.GET
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

class ApiHelper(private val apiService: ApiService,val mContext: BaseActivity) {
    private  var postAPIResponseLiveData = MutableLiveData<PostAPIResponse>()
    private val compositeSubscription = CompositeSubscription()
    fun getPost(): MutableLiveData<PostAPIResponse> {

        compositeSubscription.add(apiService.getPost()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe{
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    postResult ->
                    postAPIResponseLiveData.postValue(postResult)
                },
                { error ->
                    Log.d("error", error.message.toString())
                    Toast.makeText(mContext,(error as HttpException).message(),Toast.LENGTH_LONG).show()
                }))
            return postAPIResponseLiveData
    }
}