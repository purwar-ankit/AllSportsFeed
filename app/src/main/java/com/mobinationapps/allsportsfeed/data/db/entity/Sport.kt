package com.mobinationapps.allsportsfeed.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sports")
data class Sport(
    val idSport: Int,
    val strFormat: String?,
    val strSport: String?,
    val strSportDescription: String?,
    val strSportThumb: String?,
    val strSportThumbGreen: String?
)
{
    @PrimaryKey(autoGenerate = true)
    var _id: Int? = null
}