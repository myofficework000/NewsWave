package com.example.newswithcleancode.di

import android.content.Context
import com.example.newswithcleancode.DataConst
import com.example.newswithcleancode.R
import com.example.newswithcleancode.api.ApiNews
import com.example.newswithcleancode.api.NewsApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModules {
    @Provides
    @Singleton
    fun providesApiNews(
        @ApplicationContext context: Context
    ) = Retrofit.Builder()
        .baseUrl(DataConst.NEWS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client( OkHttpClient().newBuilder()
            .addInterceptor(NewsApiInterceptor(context.getString(R.string.CURRENTS_API_KEY)))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        )
        .build()
        .create<ApiNews>()

    @Provides
    @Singleton
    fun providesIODispatcher() = Dispatchers.IO
}