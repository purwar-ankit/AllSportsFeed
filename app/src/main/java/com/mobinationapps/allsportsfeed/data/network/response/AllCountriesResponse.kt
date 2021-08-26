package com.mobinationapps.allsportsfeed.data.network.response

import com.mobinationapps.allsportsfeed.data.db.entity.Country


data class AllCountriesResponse(
    val countries: List<Country>
)