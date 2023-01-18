package com.veselovvv.numberfactstestapp.di.core

import android.content.Context
import com.veselovvv.numberfactstestapp.core.ResourceProvider
import com.veselovvv.numberfactstestapp.presentation.facts.FactCache
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
class CoreDomainModule {
    // Declaration of annotation for IoDispatcher
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IoDispatcher

    // Declaration of annotation for MainDispatcher
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class MainDispatcher

    @IoDispatcher
    @Provides
    fun provideDispatchersIO(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun provideDispatchersMain(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    fun provideResourceProvider(
        @ApplicationContext context: Context
    ): ResourceProvider = ResourceProvider.Base(context)

    @Provides
    fun provideFactCache(@ApplicationContext context: Context): FactCache = FactCache.Base(context)
}