package com.mobinationapps.allsportsfeed

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mobinationapps.allsportsfeed.data.db.SportDao
import com.mobinationapps.allsportsfeed.data.db.SportsDatabase
import com.mobinationapps.allsportsfeed.data.network.*
import com.mobinationapps.allsportsfeed.data.repository.SportsRepository
import com.mobinationapps.allsportsfeed.data.repository.SportsRepositoryImpl
import com.mobinationapps.allsportsfeed.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AllSportsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AllSportsApplication))

        bind() from singleton { SportsDatabase(instance()) }//called invoke function of sportsDatabase
        bind() from singleton { instance<SportsDatabase>().countryLeagueDao() }
        bind() from singleton { instance<SportsDatabase>().countryDao() }
        bind() from singleton { instance<SportsDatabase>().leagueDao() }
        bind() from singleton { instance<SportsDatabase>().sportDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { SportsDbApiService(instance()) }
        bind<SportsNetworkDataSource>() with singleton { SportsNetworkDataSourceImpl(instance()) }
        bind<SportsRepository>() with singleton { SportsRepositoryImpl(instance(), instance(), instance(), instance(), instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }// doesnt need to be singleton and has no UI
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this) //initialize android three ten library, we used it for day time (ZonedTime)
    }


}