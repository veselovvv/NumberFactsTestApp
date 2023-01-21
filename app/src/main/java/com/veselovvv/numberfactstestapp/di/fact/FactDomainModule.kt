package com.veselovvv.numberfactstestapp.di.fact

import com.veselovvv.numberfactstestapp.core.ResourceProvider
import com.veselovvv.numberfactstestapp.data.fact.FactDetailsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.fact.FactRepository
import com.veselovvv.numberfactstestapp.domain.fact.BaseFactDetailsDataToDomainMapper
import com.veselovvv.numberfactstestapp.domain.fact.FactDetailsDomainToUiMapper
import com.veselovvv.numberfactstestapp.domain.fact.FetchFactUseCase
import com.veselovvv.numberfactstestapp.domain.fact.FetchRandomFactUseCase
import com.veselovvv.numberfactstestapp.presentation.fact.BaseFactDetailsDomainToUiMapper
import com.veselovvv.numberfactstestapp.presentation.fact.FactCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class FactDomainModule {
    @Provides
    fun provideFactCommunication(): FactCommunication = FactCommunication.Base()

    @Provides
    fun provideFactDetailsDataToDomainMapper(): FactDetailsDataToDomainMapper =
        BaseFactDetailsDataToDomainMapper()

    @Provides
    fun provideFetchFactUseCase(
        repository: FactRepository,
        mapper: FactDetailsDataToDomainMapper
    ): FetchFactUseCase = FetchFactUseCase.Base(repository, mapper)

    @Provides
    fun provideFetchRandomFactUseCase(
        repository: FactRepository,
        mapper: FactDetailsDataToDomainMapper
    ): FetchRandomFactUseCase = FetchRandomFactUseCase.Base(repository, mapper)

    @Provides
    fun provideFactDetailsDomainToUiMapper(
        resourceProvider: ResourceProvider
    ): FactDetailsDomainToUiMapper = BaseFactDetailsDomainToUiMapper(resourceProvider)
}