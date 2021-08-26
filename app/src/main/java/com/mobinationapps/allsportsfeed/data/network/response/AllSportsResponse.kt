package com.mobinationapps.allsportsfeed.data.network.response

import com.google.gson.annotations.SerializedName
import com.mobinationapps.allsportsfeed.data.db.entity.Sport


data class AllSportsResponse(
    //TODO: Ankit changed return data from list of sport objects to single sport object to test
@SerializedName("sports")
    val sports: List<Sport>
    //val sports: Sport
)