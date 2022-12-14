package com.veselovvv.numberfactstestapp.presentation.facts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.numberfactstestapp.di.facts.FactsDomainModule
import com.veselovvv.numberfactstestapp.domain.facts.DeleteFactsUseCase
import com.veselovvv.numberfactstestapp.domain.facts.FactsDomainToUiMapper
import com.veselovvv.numberfactstestapp.domain.facts.FetchFactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FactsViewModel @Inject constructor(
    private val communication: FactsCommunication,
    @FactsDomainModule.IoDispatcher private val dispatchersIO: CoroutineDispatcher,
    @FactsDomainModule.MainDispatcher private val dispatchersMain: CoroutineDispatcher,
    private val fetchFactsUseCase: FetchFactsUseCase,
    private val deleteFactsUseCase: DeleteFactsUseCase,
    private val mapper: FactsDomainToUiMapper,
    private val factCache: FactCache
) : ViewModel() {
    fun fetchFacts() {
        communication.map(listOf(FactUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val factsDomain = fetchFactsUseCase.execute()
            val factsUi = factsDomain.map(mapper)
            withContext(dispatchersMain) {
                factsUi.map(communication)
            }
        }
    }

    fun deleteFacts() {
        communication.map(listOf(FactUi.Progress))
        viewModelScope.launch(dispatchersIO) {
            val factsDomain = deleteFactsUseCase.execute()
            val factsUi = factsDomain.map(mapper)
            withContext(dispatchersMain) {
                factsUi.map(communication)
            }
        }
    }

    fun saveFactInfo(number: Int, fact: String) = factCache.saveFactInfo(Pair(number, fact))

    fun observe(owner: LifecycleOwner, observer: Observer<List<FactUi>>) =
        communication.observe(owner, observer)
}