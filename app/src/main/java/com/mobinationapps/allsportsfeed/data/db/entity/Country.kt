package com.mobinationapps.allsportsfeed.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countries")
data class Country(
    @SerializedName("name_en")
    val nameEn: String?
){
    @PrimaryKey(autoGenerate = true)
   // var _id: Int = 0
    var _id: Int? = null
}