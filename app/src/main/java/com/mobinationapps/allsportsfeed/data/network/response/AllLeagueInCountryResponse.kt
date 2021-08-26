package com.mobinationapps.allsportsfeed.data.network.response

import com.google.gson.annotations.SerializedName
import com.mobinationapps.allsportsfeed.data.db.entity.CountryLeagueEntry


data class AllLeagueInCountryResponse(
    @SerializedName("countrys")
    val countryLeagueEntries: List<CountryLeagueEntry>
)