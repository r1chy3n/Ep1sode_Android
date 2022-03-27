package com.javahand.ep1sode.retrofit

import com.squareup.moshi.Json

//{
//    channelid: "8",
//    serviceid: "8",
//    programname: "天才醫師耀漢:13",
//    playdate: "2022-03-10",
//    starttime: 2022-03-10 00:00:00",
//    endtime: 2022-03-10 01:00:00",
//    eventid: "000803091600",
//    programrating: "3",
//    music_channelnumber: null,
//    status: "unreservation"
//}

data class KbroProgram(
    @Json(name="channelid")
    val channelId: String,

    @Json(name="serviceid")
    val serviceId: String,

    @Json(name="programname")
    val programName: String,

    @Json(name="playdate")
    val playDate: String,

    @Json(name="starttime")
    val startTime: String,

    @Json(name="endtime")
    val endTime: String,

    @Json(name="eventid")
    val eventId: String,

    @Json(name="programrating")
    val programRating: String,

    @Json(name="music_channelnumber")
    val musicChannelNumber: String?,

    val status: String = ""
)
