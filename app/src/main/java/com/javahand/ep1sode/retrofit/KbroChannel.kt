package com.javahand.ep1sode.retrofit

import com.squareup.moshi.Json

//{
//    unique_id: "9001",
//    channelname: "節目表HD",
//    channelnumber: "1"
//}

data class KbroChannel(
    @Json(name="unique_id")
    val uniqueId: String,

    @Json(name="channelname")
    val channelName: String,

    @Json(name="channelnumber")
    val channelNumber:  String
)
