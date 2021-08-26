package com.mobinationapps.allsportsfeed.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobinationapps.allsportsfeed.data.network.response.AllCountriesResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllLeagueInCountryResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllLeaguesResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllSportsResponse
import com.mobinationapps.allsportsfeed.internal.NoConnectivityException

class SportsNetworkDataSourceImpl(
    private val sportsDbApiService: SportsDbApiService
) : SportsNetworkDataSource {

    //since LiveData is immutable so using MutableLiveData type to store updated values
    //AllLeagueInCountryResponse code block
    private val _downloadedCountryLeagueEntry = MutableLiveData<AllLeagueInCountryResponse>()
    override val downloadedCountryLeagueEntry: LiveData<AllLeagueInCountryResponse>
        get() = _downloadedCountryLeagueEntry

    override suspend fun fetchCountryLeagueEntry(country: String, sport: String) {
        try {
            val fetchedCountryLeagueEntry =
                sportsDbApiService.getAllLeaguesOfACountry(country, sport)
                    .await()
            _downloadedCountryLeagueEntry.postValue(fetchedCountryLeagueEntry)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection.", e)
        }
    }

    //AllCountriesResponse code block
    private val _downloadedCountries = MutableLiveData<AllCountriesResponse>()
    override val downloadedCountries: LiveData<AllCountriesResponse>
        get() = _downloadedCountries

    override suspend fun fetchCountries() {
        try {
            val fetchedCountries = sportsDbApiService.getAllCountries().await()
            _downloadedCountries.postValue(fetchedCountries)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection.", e)
        }
    }

   //AllSportsResponse code block
    private val _downloadedSports = MutableLiveData<AllSportsResponse>()
    override val downloadedSports: LiveData<AllSportsResponse>
        get() = _downloadedSports

    override suspend fun fetchSports() {
        try {
            val fetchedSports = sportsDbApiService
                .getAllSports().
                await()
            _downloadedSports.postValue(fetchedSports)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection.", e)
        }
    }

    //AllLeaguesResponse code block
    private val _downloadedLeagues = MutableLiveData<AllLeaguesResponse>()
    override val downloadedLeagues: LiveData<AllLeaguesResponse>
        get() = _downloadedLeagues

    override suspend fun fetchLeagues() {
        try {
            val fetchedLeagues = sportsDbApiService.getAllLeagues().await()
            _downloadedLeagues.postValue(fetchedLeagues)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection.", e)
        }
    }
}