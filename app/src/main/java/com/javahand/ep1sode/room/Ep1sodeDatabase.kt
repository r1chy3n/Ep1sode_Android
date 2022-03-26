package com.javahand.ep1sode.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ep1sodeEntity::class], version = 1, exportSchema = false)
abstract class Ep1sodeDatabase: RoomDatabase()
{
    abstract fun ep1sodeDao(): Ep1sodeDao

    companion object {

        @Volatile
        private var INSTANCE: Ep1sodeDatabase? = null

        fun getDatabase( context: Context ): Ep1sodeDatabase
        {
            return INSTANCE ?: synchronized( this ) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Ep1sodeDatabase::class.java,
                    "ep1sode_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance

                instance
            } // synchronized
        } // fun getDatabase( Context )
    } // companion object
} // abstract class Ep1sodeDatabase