package com.mobinationapps.allsportsfeed.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobinationapps.allsportsfeed.data.db.entity.Country
@Dao
interface CountryDao {
    //upsert : insert or update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(country: List<Country>)

    @Query(value = "select * from countries")
    fun getAllCountries(): LiveData<List<Country>>



}