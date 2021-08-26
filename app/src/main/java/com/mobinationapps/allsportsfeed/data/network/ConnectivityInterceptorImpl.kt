package com.mobinationapps.allsportsfeed.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.mobinationapps.allsportsfeed.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

//Implimentation class of our ConnectivityInterceptor
class ConnectivityInterceptorImpl(
    context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    //overriden method of Interceptor class
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()) {
            //custom exception created by us for network exception
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}