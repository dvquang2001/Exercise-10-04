package com.midterm.dovanquang.ui.main.viewmodel

import android.service.quicksettings.Tile
import androidx.lifecycle.LiveData
import com.midterm.dovanquang.data.model.ResponseItem
import com.midterm.dovanquang.data.repository.MainRepository
import com.midterm.dovanquang.ui.main.view.activity.BaseActivity

class DetailViewModel(private val mainRepository: MainRepository, val mContext: BaseActivity) {

    fun getParticularData(title: String): LiveData<List<ResponseItem>> {
        return mainRepository.getParticularData(title)
    }

    fun deleteData(title: String) {
        return mainRepository.deleteData(title)
    }

}