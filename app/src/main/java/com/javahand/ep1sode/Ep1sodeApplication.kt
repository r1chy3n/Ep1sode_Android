package com.javahand.ep1sode

import android.app.Application
import com.javahand.ep1sode.room.Ep1sodeDatabase
import com.javahand.ep1sode.room.Ep1sodeRepository

class Ep1sodeApplication: Application()
{
    val database by lazy {

        Ep1sodeDatabase.getDatabase( this )
    } // lazy

    val repository by lazy {

        Ep1sodeRepository( database.ep1sodeDao())
    } // lazy
} // class Ep1sodeApplication