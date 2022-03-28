package com.javahand.ep1sode.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ep1sode_table")
data class Ep1sodeEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name="channel_number")
    val channelNumber: String,

    @ColumnInfo(name="channel_name")
    val channelName: String,

    val title: String,
    val rating: Int,

    // 03/25五 20:00～21:00
    @ColumnInfo(name = "broadcast_period")
    val broadcastPeriod: String,

    // "26六 01:00、26六 10:00"
    @ColumnInfo(name = "replay_periods")
    var replayPeriods: String = ""
) // data class Ep1sodeEntity
