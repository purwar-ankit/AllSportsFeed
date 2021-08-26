package com.mobinationapps.allsportsfeed.data.network

import androidx.lifecycle.LiveData
import com.mobinationapps.allsportsfeed.data.network.response.AllCountriesResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllLeagueInCountryResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllLeaguesResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllSportsResponse

interface SportsNetworkDataSource {
    val downloadedCountryLeagueEntry: LiveData<AllLeagueInCountryResponse>
    val downloadedCountries: LiveData<AllCountriesResponse>
    val downloadedSports: LiveData<AllSportsResponse>
    val downloadedLeagues: LiveData<AllLeaguesResponse>

    suspend fun fetchCountryLeagueEntry(
        country: String,
        sport: String
    )

    suspend fun fetchCountries()
    suspend fun fetchSports()
    suspend fun fetchLeagues()


}