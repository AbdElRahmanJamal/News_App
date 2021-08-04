package com.app.newsapp.framework.remote_data_source

import android.content.Context
import com.app.core.domain.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun providesNetworkConnectivityInterceptor(@ApplicationContext context: Context): NetworkConnectivityInterceptor {
        return NetworkConnectivityInterceptor(context)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun providesOhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkConnectivityInterceptor: NetworkConnectivityInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectivityInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }
}