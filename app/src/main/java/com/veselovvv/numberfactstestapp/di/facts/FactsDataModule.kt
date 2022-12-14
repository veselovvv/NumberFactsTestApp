package com.veselovvv.numberfactstestapp.di.facts

import android.content.Context
import com.veselovvv.numberfactstestapp.data.facts.FactsDataSource
import com.veselovvv.numberfactstestapp.data.facts.FactsDbToDataMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository
import com.veselovvv.numberfactstestapp.data.facts.ToFactMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactsDataModule {
    @Provides
    @Singleton
    fun provideFactsDataSource(
        @ApplicationContext context: Context
    ): FactsDataSource = FactsDataSource.Base(context)

    @Provides
    @Singleton
    fun provideToFactMapper(): ToFactMapper = ToFactMapper.Base()

    @Provides
    @Singleton
    fun provideFactsDbToDataMapper(
        factMapper: ToFactMapper
    ): FactsDbToDataMapper = FactsDbToDataMapper.Base(factMapper)

    @Provides
    @Singleton
    fun provideFactsRepository(
        dataSource: FactsDataSource,
        mapper: FactsDbToDataMapper
    ): FactsRepository = FactsRepository.Base(dataSource, mapper)
}