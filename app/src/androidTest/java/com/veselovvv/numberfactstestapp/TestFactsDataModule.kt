package com.veselovvv.numberfactstestapp

import android.content.Context
import androidx.room.Room
import com.veselovvv.numberfactstestapp.data.core.FactsDatabase
import com.veselovvv.numberfactstestapp.data.facts.FactsDataSource
import com.veselovvv.numberfactstestapp.data.facts.FactsDbToDataMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository
import com.veselovvv.numberfactstestapp.data.facts.ToFactMapper
import com.veselovvv.numberfactstestapp.di.facts.FactsDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FactsDataModule::class] // replaces FactsDataModule with this fake one
)
class TestFactsDataModule {
    @Provides
    @Singleton
    fun provideFactsDatabase(@ApplicationContext context: Context): FactsDatabase =
        Room.inMemoryDatabaseBuilder( // uses separate data base for tests, not the original one
            context,
            FactsDatabase::class.java
        ).build()

    @Provides
    @Singleton
    fun provideFactsDataSource(
        dataBase: FactsDatabase
    ): FactsDataSource = TestFactsDataSource(dataBase)

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
