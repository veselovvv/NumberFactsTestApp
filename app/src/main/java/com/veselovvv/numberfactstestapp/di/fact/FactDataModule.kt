package com.veselovvv.numberfactstestapp.di.fact

import android.content.Context
import com.veselovvv.numberfactstestapp.data.fact.FactRepository
import com.veselovvv.numberfactstestapp.data.fact.cache.FactCacheDataSource
import com.veselovvv.numberfactstestapp.data.fact.cache.FactDataToDbMapper
import com.veselovvv.numberfactstestapp.data.fact.cloud.FactCloudDataSource
import com.veselovvv.numberfactstestapp.data.fact.cloud.FactService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactDataModule {
    companion object {
        private const val BASE_URL = "http://numbersapi.com/"
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideFactService(retrofit: Retrofit): FactService =
        retrofit.create(FactService::class.java)

    @Provides
    @Singleton
    fun provideFactCloudDataSource(service: FactService): FactCloudDataSource =
        FactCloudDataSource.Base(service)

    @Provides
    @Singleton
    fun provideFactCacheDataSource(
        @ApplicationContext context: Context
    ): FactCacheDataSource = FactCacheDataSource.Base(context)

    @Provides
    @Singleton
    fun provideFactDataToDbMapper(): FactDataToDbMapper = FactDataToDbMapper.Base()

    @Provides
    @Singleton
    fun provideFactRepository(
        cloudDataSource: FactCloudDataSource,
        cacheDataSource: FactCacheDataSource,
        factMapper: FactDataToDbMapper
    ): FactRepository = FactRepository.Base(cloudDataSource, cacheDataSource, factMapper)
}