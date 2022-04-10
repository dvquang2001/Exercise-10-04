package com.midterm.dovanquang.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "allPostTable")
data class ResponseItem(
    @PrimaryKey
    @SerializedName("title")
    var title: String = "",
    @ColumnInfo(name = "desc")
    @SerializedName("desc")
    var desc: String = "",
    @ColumnInfo(name = "timestamp")
    @SerializedName("timeStamp")
    var timeStamp: Date = Calendar.getInstance().getTime(),
    @ColumnInfo(name = "lat")
    @SerializedName("lat")
    var lat: Double = 0.0,
    @ColumnInfo(name = "lng")
    @SerializedName("lng")
    var lng: Double = 0.0,
    @ColumnInfo(name = "addr")
    @SerializedName("addr")
    var addr: String = "",
    @ColumnInfo(name = "e")
    @SerializedName("e")
    var e: String = "",
    @ColumnInfo(name = "zip")
    @SerializedName("zip")
    var zip: String = "",

)