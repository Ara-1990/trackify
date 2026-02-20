package com.the.trackify.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocationDao {
    @Insert
    suspend fun insert(location: LocationEntity)

    @Query("SELECT * FROM locations WHERE synced = 0")
    suspend fun getUnsynced(): List<LocationEntity>

//    @Query("UPDATE locations SET synced = 1 WHERE id IN (:ids)")
//    suspend fun markSynced(ids: List<Long>)


    @Query("UPDATE locations SET synced = 1 WHERE synced = 0")
    suspend fun markAsSynced()


}