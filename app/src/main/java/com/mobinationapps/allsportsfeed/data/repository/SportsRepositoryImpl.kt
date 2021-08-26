package com.mobinationapps.allsportsfeed.data.repository

import androidx.lifecycle.LiveData
import com.mobinationapps.allsportsfeed.data.db.CountryDao
import com.mobinationapps.allsportsfeed.data.db.CountryLeagueDao
import com.mobinationapps.allsportsfeed.data.db.LeagueDao
import com.mobinationapps.allsportsfeed.data.db.SportDao
import com.mobinationapps.allsportsfeed.data.db.entity.Country
import com.mobinationapps.allsportsfeed.data.db.entity.CountryLeagueEntry
import com.mobinationapps.allsportsfeed.data.db.entity.League
import com.mobinationapps.allsportsfeed.data.db.entity.Sport
import com.mobinationapps.allsportsfeed.data.network.SportsNetworkDataSource
import com.mobinationapps.allsportsfeed.data.network.response.AllCountriesResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllLeagueInCountryResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllLeaguesResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllSportsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime


class SportsRepositoryImpl(
    private val countryLeagueDao: CountryLeagueDao,
    private val countryDao: CountryDao,
    private val leagueDao: LeagueDao,
    private val sportDao: SportDao,
    private val sportsNetworkDataSource: SportsNetworkDataSource
) : SportsRepository {

    //TODO: Ankit :  null pointer exception here
    init {
        sportsNetworkDataSource.apply {
            downloadedCountryLeagueEntry.observeForever { newCountryLeagueEntry -> //persist
                persistFetchedCountryLeagueEntry(newCountryLeagueEntry)
            }
            downloadedCountries.observeForever { newCountry ->
                persistFetchedCountries(newCountry)
            }
            downloadedSports.observeForever { newSport ->
                persistFetchedSports(newSport)
            }
            sportsNetworkDataSource.downloadedLeagues.observeForever { newLeague ->
                persistFetchedLeagues(newLeague)
            }
        }
    }


    override suspend fun getCountryLeagueList(): LiveData<List<CountryLeagueEntry>> {
        return withContext(Dispatchers.IO) {
            //initCountryLeagueData()
            initSportsData()
            return@withContext countryLeagueDao.getCountryLeagueEntry()
        }
    }

    override suspend fun getCountriesList(): LiveData<List<Country>> {
        return withContext(Dispatchers.IO) {
          //  initCountryData()
            initSportsData()
            return@withContext countryDao.getAllCountries()
        }
    }

    override suspend fun getSportsList(): LiveData<List<Sport>> {
        return withContext(Dispatchers.IO) {//withContext returns a value
            initSportsData()
            return@withContext sportDao.getAllSports()
        }
    }

    override suspend fun getLeagueList(): LiveData<List<League>> {
        return withContext(Dispatchers.IO) {
          //  initLeagueData()
            initSportsData()
            return@withContext leagueDao.getAllLeague()
        }
    }

    private fun persistFetchedCountryLeagueEntry(fetchedCountryLeagues: AllLeagueInCountryResponse) {
        GlobalScope.launch(Dispatchers.IO) { //launch returns a job
            val countryLeagueEntryList = fetchedCountryLeagues.countryLeagueEntries
            //countryLeagueDao.upsert(fetchedCountryLeagues.countryLeagueEntries)
            countryLeagueDao.upsert(countryLeagueEntryList)
        }
    }

    private fun persistFetchedCountries(fetchedCountries: AllCountriesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val countryList = fetchedCountries.countries
            countryDao.upsert(countryList)
        }
    }

    //TODO: Ankit changed return data from list of sport objects to single sport object to test
    private fun persistFetchedSports(fetchedSports: AllSportsResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val sportsList = fetchedSports.sports
           // sportDao.upsert(fetchedSports.sports)
            sportDao.upsert(sportsList)
        }
    }

    private fun persistFetchedLeagues(fetchedLeagues: AllLeaguesResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val leaguesList = fetchedLeagues.leagues
            leagueDao.upsert(leaguesList)
        }
    }

 /*   private suspend fun initCountryLeagueData() {
        if (isFetchSportsNeeded(ZonedDateTime.now().minusHours(1))) { //always true for now
            fetchCountryLeagueEntry()
        }
    }*/

    private suspend fun initSportsData() {
        if (isFetchSportsNeeded(ZonedDateTime.now().minusHours(1))) { //always true for now
            fetchSport()
            fetchCountry()
            fetchLeague()
            fetchCountryLeagueEntry()
        }
    }

 /*   private suspend fun initCountryData() {
        if (isFetchSportsNeeded(ZonedDateTime.now().minusHours(1))) { //always true for now
            fetchCountry()
        }
    }

    private suspend fun initLeagueData() {
        if (isFetchSportsNeeded(ZonedDateTime.now().minusHours(1))) { //always true for now
            fetchLeague()
        }
    }*/

    private suspend fun fetchCountryLeagueEntry() {
        sportsNetworkDataSource.fetchCountryLeagueEntry("Brazil", "soccer")
    }

    private suspend fun fetchCountry() {
        sportsNetworkDataSource.fetchCountries()
    }

    private suspend fun fetchSport() {
        sportsNetworkDataSource.fetchSports()
    }

    private suspend fun fetchLeague() {
        sportsNetworkDataSource.fetchLeagues()
    }

    //TODO: make sure to correct this function as it returns true right now
    private fun isFetchSportsNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        //return lastFetchTime.isAfter(thirtyMinutesAgo)
        return true
    }

}