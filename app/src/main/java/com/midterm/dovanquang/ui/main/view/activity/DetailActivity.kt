package com.midterm.dovanquang.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.midterm.dovanquang.R
import com.midterm.dovanquang.data.api.ApiHelper
import com.midterm.dovanquang.data.api.RetrofitBuilder
import com.midterm.dovanquang.ui.base.DetailViewModelFactory
import com.midterm.dovanquang.ui.main.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*
import kotlin.math.ln

class DetailActivity : BaseActivity(){

    private var title: String = ""
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if(intent.hasExtra("title")) {
            title = intent.getStringExtra("title")!!
        }
        setUpViewModel()
        if(title != "")
            fetchDataObserver()
    }

    private fun fetchDataObserver() {
        viewModel.getParticularData(title)
            .observe(this, Observer {
                if(it.isNotEmpty()) {
                    setData(it[0].title,it[0].desc,it[0].timeStamp,
                    it[0].lat,it[0].lng,it[0].addr,it[0].e,it[0].zip)
                }
            })
    }

    private fun setData(
        title: String,
        desc: String,
        timeStamp: Date,
        lat: Double,
        lng: Double,
        addr: String,
        e: String,
        zip: String
    ) {
        txtTitle.text = title
        txtDesc.text = desc
        txtTimestamp.text = timeStamp.toString()
        txtLat.text = lat.toString()
        txtLng.text = lng.toString()
        txtAddr.text = addr
        txtE.text = e
        txtZip.text = zip

    }


    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            DetailViewModelFactory(ApiHelper(RetrofitBuilder.apiServie,this),this)
        ).get(DetailViewModel::class.java)
    }


}

private fun ViewModelProvider.get(java: Class<DetailViewModel>): DetailViewModel {
    TODO("Not yet implemented")
}
