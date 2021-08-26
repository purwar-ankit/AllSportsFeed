package com.mobinationapps.allsportsfeed.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobinationapps.allsportsfeed.data.db.entity.Country
import com.mobinationapps.allsportsfeed.data.db.entity.CountryLeagueEntry
import com.mobinationapps.allsportsfeed.data.db.entity.League
import com.mobinationapps.allsportsfeed.data.db.entity.Sport


@Database(
    entities = [Country::class, Sport::class, League::class, CountryLeagueEntry::class],
    version = 1
)
abstract class SportsDatabase : RoomDatabase() {
    //initialize dao interface
    abstract fun countryDao() : CountryDao
    abstract fun sportDao() : SportDao
    abstract fun leagueDao() : LeagueDao
    abstract fun countryLeagueDao(): CountryLeagueDao

    //to create singleton
    companion object{
        //create instance
        @Volatile private var instance: SportsDatabase? = null
        private val LOCK = Any()

        //initialize instance
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        //build db
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            SportsDatabase::class.java, "SportsDatabase.db")
                .build()

    }
}