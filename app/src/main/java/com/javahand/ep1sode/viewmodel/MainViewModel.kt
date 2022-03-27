package com.javahand.ep1sode.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.javahand.ep1sode.retrofit.KbroRestful
import com.javahand.ep1sode.room.Ep1sodeEntity
import com.javahand.ep1sode.room.Ep1sodeRepository
import kotlinx.coroutines.launch

class MainViewModel(private val ep1Repo: Ep1sodeRepository) : ViewModel()
{
    val allEp1sodes: LiveData<List<Ep1sodeEntity>> =
        ep1Repo.allEp1sodes.asLiveData()

    init
    {
        fetchFromKbro()
    } // init

    private fun fetchFromKbro()
    {
        viewModelScope.launch {

            KbroRestful.retrofitService.getAllChannelList(
                System.currentTimeMillis()
            ).data.forEach {

//                KbroRestful.retrofitService.getProgramList( it.uniqueId, )
            } // forEach
        } // launch
    } // fun fetchFromKbro()

    fun insert(ep1sodeEntity: Ep1sodeEntity) = viewModelScope.launch {

        ep1Repo.insert(ep1sodeEntity)
    } // launch
} // class MainViewModel