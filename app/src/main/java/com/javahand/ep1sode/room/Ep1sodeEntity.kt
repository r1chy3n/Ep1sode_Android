package com.javahand.ep1sode.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ep1sode_table")
data class Ep1sodeEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name="channel_number")
    val channelNumber: Short,

    @ColumnInfo(name="channel_name")
    val channelName: String,

    val title: String,
    val rating: String,

    @ColumnInfo(name = "start_time")
    val startTime: Long,

    @ColumnInfo(name = "end_time")
    val endTime: Long,

    // "[Long]|[Long]|..."
    @ColumnInfo(name = "replay_times")
    val replayTimes: String = ""
) // data class Ep1sodeEntity
