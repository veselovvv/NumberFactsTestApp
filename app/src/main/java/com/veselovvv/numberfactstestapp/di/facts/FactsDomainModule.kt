package com.veselovvv.numberfactstestapp.di.facts

import com.veselovvv.numberfactstestapp.core.ResourceProvider
import com.veselovvv.numberfactstestapp.data.facts.FactDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository
import com.veselovvv.numberfactstestapp.domain.facts.*
import com.veselovvv.numberfactstestapp.presentation.facts.BaseFactDomainToUiMapper
import com.veselovvv.numberfactstestapp.presentation.facts.BaseFactsDomainToUiMapper
import com.veselovvv.numberfactstestapp.presentation.facts.FactsCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class FactsDomainModule {
    @Provides
    fun provideFactsCommunication(): FactsCommunication = FactsCommunication.Base()

    @Provides
    fun provideFactDataToDomainMapper(): FactDataToDomainMapper = BaseFactDataToDomainMapper()

    @Provides
    fun provideFactsDataToDomainMapper(
        factMapper: FactDataToDomainMapper
    ): FactsDataToDomainMapper = BaseFactsDataToDomainMapper(factMapper)

    @Provides
    fun provideFetchFactsUseCase(
        repository: FactsRepository,
        factsMapper: FactsDataToDomainMapper
    ): FetchFactsUseCase = FetchFactsUseCase.Base(repository, factsMapper)

    @Provides
    fun provideDeleteFactsUseCase(
        repository: FactsRepository,
        factsMapper: FactsDataToDomainMapper
    ): DeleteFactsUseCase = DeleteFactsUseCase.Base(repository, factsMapper)

    @Provides
    fun provideFactDomainToUiMapper(): FactDomainToUiMapper = BaseFactDomainToUiMapper()

    @Provides
    fun provideFactsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        factMapper: FactDomainToUiMapper
    ): FactsDomainToUiMapper = BaseFactsDomainToUiMapper(resourceProvider, factMapper)
}