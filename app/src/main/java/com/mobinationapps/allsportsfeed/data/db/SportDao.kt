package com.mobinationapps.allsportsfeed.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobinationapps.allsportsfeed.data.db.entity.Sport

@Dao
interface SportDao {
    //TODO: Ankit changed return data from list of sport objects to single sport object to test

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(sport: List<Sport>)
    //fun upsert(sport: Sport)

    @Query(value = "select * from sports")
    fun getAllSports() : LiveData<List<Sport>>

    @Query(value = "select * from sports where _id = :id")
    fun getSportDetail(id : Int) : LiveData<Sport>

}