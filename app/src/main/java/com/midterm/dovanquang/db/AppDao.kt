package com.midterm.dovanquang.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.midterm.dovanquang.data.model.ResponseItem
import javax.sql.DataSource

@Dao
interface AppDao {
    @Insert
    fun insertAll( data: ResponseItem)

    @Query("SELECT * FROM allPostTable")
    fun getPost(): androidx.paging.DataSource.Factory<Int, ResponseItem>

    @Query("SELECT title,`desc`,timestamp FROM allPostTable")
    fun getParticularData(): LiveData<List<ResponseItem>>

    @Query("DELETE FROM allPostTable WHERE title = title")
    fun delete(title: String)
}