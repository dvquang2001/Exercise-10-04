package com.midterm.dovanquang.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.midterm.dovanquang.data.model.PostAPIResponse
import com.midterm.dovanquang.data.model.ResponseItem
import com.midterm.dovanquang.data.repository.MainRepository
import com.midterm.dovanquang.ui.main.view.activity.BaseActivity

class MainActivityViewModel(private val mainRepository: MainRepository, val mContext: BaseActivity) : ViewModel() {

    fun getPost(): MutableLiveData<PostAPIResponse> {
        return mainRepository.getPost()
    }


    fun insertData(data: ResponseItem) {
        mainRepository.insertData(data)
    }


    fun getAllPost(): LiveData<PagedList<ResponseItem>> {
        return mainRepository.getAllPost()
    }


}