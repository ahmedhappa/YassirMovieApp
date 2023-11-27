package com.example.yassirmovieapp.di

import android.content.Context
import androidx.room.Room
import com.example.yassirmovieapp.BuildConfig
import com.example.yassirmovieapp.data.local.database.MovieAppDatabase
import com.example.yassirmovieapp.data.remote.api.MovieAppApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DATABASE_NAME = "movie_app_database"

    private const val HEADER_ACCEPT = "Accept"
    private const val HEADER_CONTENT_TYPE = "Content-Type"
    private const val HEADER_AUTHORIZATION = "Authorization"

    private const val HEADER_VALUE_APPLICATION_JSON = "application/json"

    @Provides
    @Singleton
    fun provideMovieAppDatabase(
        @ApplicationContext context: Context
    ): MovieAppDatabase {
        return Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpHeadersInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpHeadersInterceptor)
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.apply {
                addInterceptor(httpLoggingInterceptor)
            }
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHttpHeadersInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()

            val headersMap = mutableMapOf(
                HEADER_ACCEPT to HEADER_VALUE_APPLICATION_JSON,
                HEADER_CONTENT_TYPE to HEADER_VALUE_APPLICATION_JSON,
                HEADER_AUTHORIZATION to "Bearer ${BuildConfig.API_KEY}"
            )

            val newRequest = originalRequest.newBuilder()
                .headers(headersMap.toHeaders())
                .build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieAppApi(retrofit: Retrofit): MovieAppApi {
        return retrofit.create(MovieAppApi::class.java)
    }
}