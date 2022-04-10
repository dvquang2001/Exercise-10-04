package com.midterm.dovanquang.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.midterm.dovanquang.data.api.ApiHelper
import com.midterm.dovanquang.data.model.PostAPIResponse
import com.midterm.dovanquang.data.model.ResponseItem
import com.midterm.dovanquang.db.AppDatabase
import com.midterm.dovanquang.ui.main.view.activity.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.sql.DataSource

class MainRepository(private val apiHelper: ApiHelper, val mContext: BaseActivity) {
    var appDatabase: AppDatabase? = null
    fun getPost(): MutableLiveData<PostAPIResponse> {
        return apiHelper.getPost()
    }

    fun initializeDB(context: Context): AppDatabase {
        return AppDatabase.getDatabaseClient(context)
    }

    fun insertData(data: ResponseItem) {

        appDatabase = initializeDB(mContext)

        CoroutineScope(Dispatchers.IO).launch {
            appDatabase!!.appDao().insertAll(data)
        }
    }

    fun getAllPost(): LiveData<PagedList<ResponseItem>> {
        appDatabase = initializeDB(mContext)
        var personsLiveData: LiveData<PagedList<ResponseItem>>
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        val factory: androidx.paging.DataSource.Factory<Int, ResponseItem> =
            appDatabase!!.appDao().getPost()


        val data: LivePagedListBuilder<Int, ResponseItem> = LivePagedListBuilder(factory, config)
        personsLiveData = data.build()

        return personsLiveData

    }

    fun getParticularData(title: String): LiveData<List<ResponseItem>> {
        appDatabase = initializeDB(mContext)
        return appDatabase!!.appDao().getParticularData()
    }

    fun deleteData(title: String) {
        appDatabase = initializeDB(mContext)
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase!!.appDao().delete(title)
        }
    }
}
