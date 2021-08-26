package com.mobinationapps.allsportsfeed.data.network.response

import com.google.gson.annotations.SerializedName
import com.mobinationapps.allsportsfeed.data.db.entity.League


data class AllLeaguesResponse(
    @SerializedName("leagues")
    val leagues: List<League>
)