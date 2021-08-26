package com.mobinationapps.allsportsfeed.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mobinationapps.allsportsfeed.data.network.response.AllLeagueInCountryResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllCountriesResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllLeaguesResponse
import com.mobinationapps.allsportsfeed.data.network.response.AllSportsResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "1"
const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/$API_KEY/"
/*
* List all sports
https://www.thesportsdb.com/api/v1/json/1/all_sports.php

List all leagues
https://www.thesportsdb.com/api/v1/json/1/all_leagues.php

List all countries
https://www.thesportsdb.com/api/v1/json/1/all_countries.php

List all Leagues in a country
https://www.thesportsdb.com/api/v1/json/1/search_all_leagues.php?c=England
https://www.thesportsdb.com/api/v1/json/1/search_all_leagues.php?c=England&s=Soccer

List all Seasons in a League
https://www.thesportsdb.com/api/v1/json/1/search_all_seasons.php?id=4328

List all Teams in a League
https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League
https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?s=Soccer&c=Spain

List All teams details in a league by Id
https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4328*/

interface SportsDbApiService {
    //https://www.thesportsdb.com/api/v1/json/1/all_sports.php
    @GET("all_sports.php")
    fun getAllSports(): Deferred<AllSportsResponse>

    @GET("all_countries.php")
    fun getAllCountries():Deferred<AllCountriesResponse>

    @GET("all_leagues.php")
    fun getAllLeagues():Deferred<AllLeaguesResponse>

    //https://www.thesportsdb.com/api/v1/json/1/search_all_leagues.php?c=England&s=Soccer
    @GET("search_all_leagues.php")
    fun getAllLeaguesOfACountry(
        @Query("c") country: String,
        @Query("s") sport: String = "Soccer"
    ): Deferred<AllLeagueInCountryResponse>


    companion object {
        operator fun invoke(
            //prepared for dependency injection
        connectivityInterceptor: ConnectivityInterceptor
        ): SportsDbApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    //.addQueryParameter("key",API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)//adding interceptor to check if network is connected/available or not
                .build()

            return Retrofit.Builder()
                .client(okkHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SportsDbApiService::class.java)
        }
    }



}