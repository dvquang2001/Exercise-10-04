package com.midterm.dovanquang.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.midterm.dovanquang.R
import com.midterm.dovanquang.data.api.ApiHelper
import com.midterm.dovanquang.data.api.RetrofitBuilder
import com.midterm.dovanquang.data.model.PostAPIResponse
import com.midterm.dovanquang.data.model.ResponseItem
import com.midterm.dovanquang.ui.base.ViewModelFactory
import com.midterm.dovanquang.ui.main.view.adapter.AllPostAdapter
import com.midterm.dovanquang.ui.main.view.adapter.AllPostPagedAdapter
import com.midterm.dovanquang.ui.main.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mAdapter: AllPostAdapter
    private lateinit var pagedAdapter: AllPostPagedAdapter
    private var list = ArrayList<ResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()
        setUpDataBaseObserver()
    }

    private fun setUpDataBaseObserver() {

        pagedAdapter = AllPostPagedAdapter(this)
        rcv_post.apply {
            setHasFixedSize(true)
            layoutManager =  LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            adapter = pagedAdapter
        }

        viewModel.getAllPost()
            .observe(this, Observer {
                if(it.size != 0) {
                    pagedAdapter.submitList(it)
                    rcv_post.adapter = pagedAdapter
                }
                else {
                    setUPApiCallObserver()
                }
            })
    }

    private fun setUPApiCallObserver() {
        viewModel.getPost()
            .observe(this, Observer {
                if(it.isNotEmpty())
                {
                    insertDataToDb(it)
                }
            })
    }

    private fun insertDataToDb(postAPIResponse: PostAPIResponse) {
        for(i in postAPIResponse.indices){
            var model=ResponseItem()
            model.title = postAPIResponse[i].title
            model.desc = postAPIResponse[i].desc
            model.timeStamp = postAPIResponse[i].timeStamp
            model.lat = postAPIResponse[i].lat
            model.lng = postAPIResponse[i].lng
            model.addr = postAPIResponse[i].addr
            model.e = postAPIResponse[i].e
            model.zip = postAPIResponse[i].zip
            viewModel.insertData(model)

        }

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService,this),this)
        ).get(MainActivityViewModel::class.java)

    }
}