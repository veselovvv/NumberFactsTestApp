package com.veselovvv.numberfactstestapp

import com.veselovvv.numberfactstestapp.data.core.FactsDatabase
import com.veselovvv.numberfactstestapp.data.fact.FactRepository
import com.veselovvv.numberfactstestapp.data.fact.cache.FactCacheDataSource
import com.veselovvv.numberfactstestapp.data.fact.cache.FactDataToDbMapper
import com.veselovvv.numberfactstestapp.data.fact.cloud.FactCloudDataSource
import com.veselovvv.numberfactstestapp.di.fact.FactDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FactDataModule::class] // replaces FactDataModule with this fake one
)
class TestFactDataModule {
    @Provides
    @Singleton
    fun provideFactCloudDataSource(): FactCloudDataSource = TestFactCloudDataSource()

    @Provides
    @Singleton
    fun provideFactCacheDataSource(
        database: FactsDatabase
    ): FactCacheDataSource = TestFactCacheDataSource(database)

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
