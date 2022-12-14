package com.veselovvv.numberfactstestapp.di.facts

import android.content.Context
import com.veselovvv.numberfactstestapp.core.ResourceProvider
import com.veselovvv.numberfactstestapp.data.facts.FactDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsDataToDomainMapper
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository
import com.veselovvv.numberfactstestapp.domain.facts.*
import com.veselovvv.numberfactstestapp.presentation.facts.BaseFactDomainToUiMapper
import com.veselovvv.numberfactstestapp.presentation.facts.BaseFactsDomainToUiMapper
import com.veselovvv.numberfactstestapp.presentation.facts.FactCache
import com.veselovvv.numberfactstestapp.presentation.facts.FactsCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
class FactsDomainModule {
    @Provides
    fun provideFactsCommunication(): FactsCommunication = FactsCommunication.Base()

    @IoDispatcher
    @Provides
    fun provideDispatchersIO(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun provideDispatchersMain(): CoroutineDispatcher = Dispatchers.Main

    // Declaration of annotation for IoDispatcher
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IoDispatcher

    // Declaration of annotation for MainDispatcher
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class MainDispatcher

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

    //TODO move to core module things that are used in different places here and further
    @Provides
    fun provideResourceProvider(
        @ApplicationContext context: Context
    ): ResourceProvider = ResourceProvider.Base(context)

    @Provides
    fun provideFactDomainToUiMapper(): FactDomainToUiMapper = BaseFactDomainToUiMapper()

    @Provides
    fun provideFactsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        factMapper: FactDomainToUiMapper
    ): FactsDomainToUiMapper = BaseFactsDomainToUiMapper(resourceProvider, factMapper)

    @Provides
    fun provideFactCache(@ApplicationContext context: Context): FactCache = FactCache.Base(context)
}