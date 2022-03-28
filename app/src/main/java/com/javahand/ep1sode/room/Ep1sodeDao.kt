package com.javahand.ep1sode.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface Ep1sodeDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ep1sodeEntity: Ep1sodeEntity)

    @Update
    suspend fun update( ep1sodeEntity: Ep1sodeEntity )

    @Query("SELECT * FROM ep1sode_table ORDER BY broadcast_period")
    fun getAllEp1sodes(): Flow<List<Ep1sodeEntity>>

    @Query(
        "SELECT * FROM ep1sode_table WHERE "
                + "channel_number=:channelNumber AND title=:title"
    )
    suspend fun getEp1sodes(
        channelNumber: String,
        title: String
    ): List<Ep1sodeEntity>
} // interface Ep1sodeDao