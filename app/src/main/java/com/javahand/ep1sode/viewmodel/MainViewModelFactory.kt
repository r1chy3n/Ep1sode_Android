package com.javahand.ep1sode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javahand.ep1sode.room.Ep1sodeRepository

class MainViewModelFactory(private val ep1sodeRepository: Ep1sodeRepository) :
    ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if ( modelClass.isAssignableFrom( MainViewModel::class.java ))
        {
            @Suppress( "UNCHECKED_CAST" )
            return MainViewModel( ep1sodeRepository ) as T
        } // if

        throw IllegalArgumentException( "Unknown ViewModel class" )
    } // fun create( Class<T>)
} // class MainViewModelFactory