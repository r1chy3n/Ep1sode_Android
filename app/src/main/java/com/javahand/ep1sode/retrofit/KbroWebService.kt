package com.javahand.ep1sode.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.kbro.com.tw/do/"

private val moshi = Moshi.Builder()
    .add( KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory( MoshiConverterFactory.create( moshi ))
    .baseUrl( BASE_URL ).build()

interface KbroWebService
{
    @GET ( "getpage_catvtable.php?callback=&action=get_channel_alllist"
            + "&sysIdentifier=5" )
    suspend fun getAllChannelList(
        @Query( "_" ) currentMillis: Long ): KbroAllChannelResult

    @GET ( "getpage_catvtable.php?callback=&action=get_channelprogram" )
    suspend fun getProgramList(
        @Query( "channelid" ) channelId: String,
        @Query( "showtime" ) showTime: String,
        @Query( "_" ) currentMillis: Long
    ): KbroChannelProgramResult
} // interface KbroCatvService

object KbroRestful
{
    val retrofitService: KbroWebService by lazy {

        retrofit.create( KbroWebService::class.java )
    } // val retrofitService
} // object KbroCatv
