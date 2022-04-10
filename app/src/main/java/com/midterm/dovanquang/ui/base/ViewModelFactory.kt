package com.midterm.dovanquang.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.midterm.dovanquang.data.api.ApiHelper
import com.midterm.dovanquang.data.repository.MainRepository
import com.midterm.dovanquang.ui.main.view.activity.BaseActivity
import com.midterm.dovanquang.ui.main.viewmodel.MainActivityViewModel

class ViewModelFactory (private val apiHelper: ApiHelper, val mContext: BaseActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(MainRepository(apiHelper,mContext),mContext) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}