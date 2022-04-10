package com.midterm.dovanquang.ui.main.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.midterm.dovanquang.data.model.ResponseItem

class DiffCallback : DiffUtil.ItemCallback<ResponseItem>(){
    override fun areItemsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
        return oldItem == newItem
    }
}