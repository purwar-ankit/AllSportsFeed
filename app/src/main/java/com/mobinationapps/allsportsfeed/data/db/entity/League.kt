package com.mobinationapps.allsportsfeed.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "leagues")
data class League(
    val idLeague: Int,
    val strLeague: String,
    val strLeagueAlternate: String?,
    val strSport: String
)
{
    @PrimaryKey(autoGenerate = true)
    //var _id: Int = 0
    var _id: Int? = null
}