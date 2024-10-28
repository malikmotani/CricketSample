package com.sapient.cricketsample.di

import com.sapient.cricketsample.BuildConfig
import com.sapient.cricketsample.common.AppConstants.BASE_URL
import com.sapient.cricketsample.data.repository.CricketRepositoryImpl
import com.sapient.cricketsample.data.service.CricketService
import com.sapient.cricketsample.domain.source.CricketRemoteDataSource
import com.sapient.cricketsample.data.source.CricketRemoteDataSourceImpl
import com.sapient.cricketsample.domain.repository.CricketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptors: ArrayList<Interceptor>,
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }

    @Singleton
    @Provides
    fun provideCricketService(retrofit: Retrofit): CricketService {
        return retrofit.create(CricketService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(cricketRemoteDataSource: CricketRemoteDataSource): CricketRepository {
        return CricketRepositoryImpl(cricketRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideCricketRemoteDataSource(cricketService: CricketService): CricketRemoteDataSource {
        return CricketRemoteDataSourceImpl(cricketService)
    }
}