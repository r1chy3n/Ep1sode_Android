package com.javahand.ep1sode.retrofit

//{
//    result: true,
//    data:
//    [
//        {
//            channelid: "8",
//            serviceid: "8",
//            programname: "天才醫師耀漢:13",
//            playdate: "2022-03-10",
//            starttime: 2022-03-10 00:00:00",
//            endtime: 2022-03-10 01:00:00",
//            eventid: "000803091600",
//            programrating: "3",
//            music_channelnumber: null,
//            status: "unreservation"
//        },
//    ...
//    ],
//    msg: ""
//}

data class KbroAllChannelResult(
    val result: Boolean,
    val data:   List<KbroChannel>,
    val msg:    String
)
