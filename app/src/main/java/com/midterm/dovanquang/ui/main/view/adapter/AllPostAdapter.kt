package com.midterm.dovanquang.ui.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.midterm.dovanquang.R
import com.midterm.dovanquang.data.model.ResponseItem
import com.midterm.dovanquang.ui.main.view.activity.MainActivity

class AllPostAdapter (val list: List<ResponseItem>, val mContext: MainActivity) : RecyclerView.Adapter<AllPostAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
        val txtDesc = itemView.findViewById(R.id.txtDesc) as TextView
        val txtTimestamp = itemView.findViewById(R.id.txtTimestamp) as TextView
        val txtLat = itemView.findViewById(R.id.txtLat) as TextView
        val txtLng = itemView.findViewById(R.id.txtLng) as TextView
        val txtAddr = itemView.findViewById(R.id.txtAddr) as TextView
        val txtE = itemView.findViewById(R.id.txtE) as TextView
        val txtZip = itemView.findViewById(R.id.txtZip) as TextView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPostAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.item, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: AllPostAdapter.ViewHolder, position: Int) {


        val model: ResponseItem = list[position]
        holder.txtTitle.text = model!!.title
        holder.txtDesc.text = model.desc
        holder.txtTimestamp.text = model.timeStamp.toString()
        holder.txtLat.text = model.lat.toString()
        holder.txtLng.text = model.lng.toString()
        holder.txtAddr.text = model.addr
        holder.txtE.text = model.e
        holder.txtZip.text = model.zip
    }

    override fun getItemCount(): Int {
        return list.size
    }


}