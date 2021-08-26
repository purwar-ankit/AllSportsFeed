package com.mobinationapps.allsportsfeed.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "all_leagues_in_country")
data class CountryLeagueEntry(
    val dateFirstEvent: String??,
    val idAPIfootball: Int?,
    val idCup: Int?,
    val idLeague: Int?,
    val idSoccerXML: Int?,
    val intFormedYear: Int?,
    val strBadge: String?,
    val strBanner: String?,
    val strComplete: String?,
    val strCountry: String?,
    val strCurrentSeason: String?,
    val strDescriptionCN: String?,
    val strDescriptionDE: String?,
    val strDescriptionEN: String?,
    val strDescriptionES: String?,
    val strDescriptionFR: String?,
    val strDescriptionHU: String?,
    val strDescriptionIL: String?,
    val strDescriptionIT: String?,
    val strDescriptionJP: String?,
    val strDescriptionNL: String?,
    val strDescriptionNO: String?,
    val strDescriptionPL: String?,
    val strDescriptionPT: String?,
    val strDescriptionRU: String?,
    val strDescriptionSE: String?,
    val strDivision: String?,
    val strFacebook: String?,
    val strFanart1: String?,
    val strFanart2: String?,
    val strFanart3: String?,
    val strFanart4: String?,
    val strGender: String?,
    val strLeague: String?,
    val strLeagueAlternate: String?,
    val strLocked: String?,
    val strLogo: String?,
    val strNaming: String?,
    val strPoster: String?,
    val strRSS: String?,
    val strSport: String?,
    val strTrophy: String?,
    val strTwitter: String?,
    val strWebsite: String?,
    val strYoutube: String?
){
    @PrimaryKey(autoGenerate = true)
    //var _id: Int = 0
    var _id: Int? = null
}