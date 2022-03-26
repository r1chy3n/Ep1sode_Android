package com.javahand.ep1sode.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Ep1sodeRepository( private val ep1sodeDao: Ep1sodeDao )
{
    val allEp1sodes: Flow<List<Ep1sodeEntity>> = ep1sodeDao.getAllEp1sodes()

    @WorkerThread
    suspend fun insert( ep1sodeEntity: Ep1sodeEntity )
    {
        ep1sodeDao.insert( ep1sodeEntity )
    } // fun insert( Ep1sodeEntity)
} // class Ep1sodeRepository( Ep1sodeDao )