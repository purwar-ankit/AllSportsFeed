package com.mobinationapps.allsportsfeed.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobinationapps.allsportsfeed.data.db.entity.CountryLeagueEntry

@Dao
interface CountryLeagueDao {
    //upsert : insert or update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(countryLeagueEntry: List<CountryLeagueEntry>)

    @Query(value = "select * from all_leagues_in_country")
    fun getCountryLeagueEntry(): LiveData<List<CountryLeagueEntry>>


}