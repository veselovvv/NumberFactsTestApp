package com.veselovvv.numberfactstestapp.presentation.facts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.numberfactstestapp.di.core.CoreDomainModule
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
    @CoreDomainModule.IoDispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersMain: CoroutineDispatcher,
    private val fetchFactsUseCase: FetchFactsUseCase,
    private val deleteFactsUseCase: DeleteFactsUseCase,
    private val mapper: FactsDomainToUiMapper,
    private val factCache: FactCache
) : ViewModel() {
    fun fetchFacts() {
        viewModelScope.launch(dispatchersIO) {
            val factsDomain = fetchFactsUseCase.execute()
            val factsUi = factsDomain.map(mapper)
            withContext(dispatchersMain) {
                factsUi.map(communication)
            }
        }
    }

    fun deleteFacts() {
        viewModelScope.launch(dispatchersIO) {
            val factsDomain = deleteFactsUseCase.execute()
            val factsUi = factsDomain.map(mapper)
            withContext(dispatchersMain) {
                factsUi.map(communication)
            }
        }
    }

    fun saveFactInfo(number: String, fact: String) = factCache.saveFactInfo(Pair(number, fact))

    fun getFactsLiveData() = communication.getLiveData()
}