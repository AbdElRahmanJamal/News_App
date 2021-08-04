package com.app.newsapp.framework.remote_data_source

import android.content.Context
import com.app.core.domain.util.AppExceptions
import com.app.core.domain.util.NetworkUtils.Companion.isInternetAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectivityInterceptor
@Inject constructor(@ApplicationContext private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable(context))
            throw AppExceptions.NoConnectivityException
        return chain.proceed(chain.request())
    }
}