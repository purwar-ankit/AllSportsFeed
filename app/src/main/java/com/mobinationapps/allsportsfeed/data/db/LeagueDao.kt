package com.mobinationapps.allsportsfeed.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobinationapps.allsportsfeed.data.db.entity.League
import org.threeten.bp.LocalDate

@Dao
interface LeagueDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(league: List<League>)

    @Query(value = "select * from leagues")
    fun getAllLeague(): LiveData<List<League>>

    //TODO: Complete this incomplete date query as per requirement
   // @Query("delete from leagues")
    //fun deleteOldEntries(firstDateToKeep: LocalDate)
}