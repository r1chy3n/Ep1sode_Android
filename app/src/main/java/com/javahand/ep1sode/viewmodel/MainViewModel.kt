package com.javahand.ep1sode.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.javahand.ep1sode.retrofit.KbroChannel
import com.javahand.ep1sode.retrofit.KbroProgram
import com.javahand.ep1sode.retrofit.KbroRestful
import com.javahand.ep1sode.room.Ep1sodeEntity
import com.javahand.ep1sode.room.Ep1sodeRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(private val ep1Repo: Ep1sodeRepository) : ViewModel()
{
    private val yyyyMMdd = SimpleDateFormat("yyyyMMdd", Locale.TAIWAN)
    private val yyyy_MM_dd = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN)

    val allEp1sodes: LiveData<List<Ep1sodeEntity>> =
        ep1Repo.allEp1sodes.asLiveData()

    val fetchingFinished = MutableLiveData( false )

    fun fetchFromKbro()
    {
        viewModelScope.launch {

            ep1Repo.deletePriorTo( yyyy_MM_dd.format( Date()))

            KbroRestful.retrofitService.getAllChannelList(
                System.currentTimeMillis()
            ).data.let { kbroChannelList ->

                val dateList = List(8) { dDay ->

                    yyyyMMdd.format(
                        Calendar.getInstance().apply {

                            add(Calendar.DATE, dDay)
                        }.time
                    ) // format
                }.forEach { date ->

                    kbroChannelList.forEach { kbroChannel ->

                        fetchProgram(date, kbroChannel)

                        Thread.sleep( 50 )
                    } // forEach
                } // forEach
            } // let

            fetchingFinished.value = true
        } // launch
    } // fun fetchFromKbro()

    private suspend fun fetchProgram(date: String, kbroChannel: KbroChannel)
    {
        KbroRestful.retrofitService.getProgramList(
            kbroChannel.uniqueId,
            date,
            System.currentTimeMillis()
        ).data.forEach { kbroProgram ->

            if (kbroProgram.programName.endsWith(":1"))
            {
                val ep1sodes = ep1Repo.getEp1sodes(
                    kbroChannel.channelNumber,
                    kbroProgram.programName
                )

                if (ep1sodes.isEmpty())
                {
                    insertEp1sode(kbroChannel, kbroProgram)
                }
                else
                {
                    updateEp1sode(ep1sodes.first(), kbroProgram)
                } // if - else
            } // if
        } // forEach
    } // fun fetchProgram( String, KbroChannel )

    private suspend fun updateEp1sode(
        ep1sodeEntity: Ep1sodeEntity,
        kbroProgram: KbroProgram
    )
    {
        val replayPeriod = kbroProgram.startTime.substring(5, 16)

        if (!ep1sodeEntity.replayPeriods.contains(replayPeriod))
        {
            if (ep1sodeEntity.replayPeriods.isNotEmpty())
            {
                ep1sodeEntity.replayPeriods += " / "
            } // if

            ep1sodeEntity.replayPeriods += replayPeriod

            ep1Repo.update(ep1sodeEntity)
        } // if
    } // fun updateEp1sode( KbroProgram, Ep1sodeEntity )

    private fun insertEp1sode(
        kbroChannel: KbroChannel,
        kbroProgram: KbroProgram
    )
    {
        val broadcastPeriod = kbroProgram.startTime.substring(0, 16) +
                " ～ " + kbroProgram.endTime.substring(11, 16)

        insert(
            Ep1sodeEntity(
                kbroProgram.eventId.toLong(),
                kbroChannel.channelNumber,
                kbroChannel.channelName,
                kbroProgram.programName,
                kbroProgram.programRating.toInt(),
                broadcastPeriod
            )
        )
    } // fun insertEp1sode( KbroChannel, KbroProgram )

    fun insert(ep1sodeEntity: Ep1sodeEntity) = viewModelScope.launch {

        ep1Repo.insert(ep1sodeEntity)
    } // launch
} // class MainViewModel