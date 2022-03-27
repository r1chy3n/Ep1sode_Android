package com.javahand.ep1sode.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Ep1sodeDao
{
    @Insert
    suspend fun insert( ep1sodeEntity: Ep1sodeEntity )

    @Query( "SELECT * FROM ep1sode_table ORDER BY broadcast_period" )
    fun getAllEp1sodes(): Flow<List<Ep1sodeEntity>>
} // interface Ep1sodeDao