package com.mobinationapps.allsportsfeed.data.repository

import androidx.lifecycle.LiveData
import com.mobinationapps.allsportsfeed.data.db.entity.Country
import com.mobinationapps.allsportsfeed.data.db.entity.CountryLeagueEntry
import com.mobinationapps.allsportsfeed.data.db.entity.League
import com.mobinationapps.allsportsfeed.data.db.entity.Sport


interface SportsRepository {
    suspend fun getCountryLeagueList() : LiveData<List<CountryLeagueEntry>>
    suspend fun getCountriesList() : LiveData<List<Country>>
    suspend fun getSportsList(): LiveData<List<Sport>>
    suspend fun getLeagueList():LiveData<List<League>>

}