package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.numberfactstestapp.di.core.CoreDomainModule
import com.veselovvv.numberfactstestapp.domain.fact.FactDetailsDomainToUiMapper
import com.veselovvv.numberfactstestapp.domain.fact.FetchFactUseCase
import com.veselovvv.numberfactstestapp.domain.fact.FetchRandomFactUseCase
import com.veselovvv.numberfactstestapp.presentation.facts.FactCache
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val communication: FactCommunication,
    @CoreDomainModule.IoDispatcher private val dispatchersIO: CoroutineDispatcher,
    @CoreDomainModule.MainDispatcher private val dispatchersMain: CoroutineDispatcher,
    private val fetchFactUseCase: FetchFactUseCase,
    private val fetchRandomFactUseCase: FetchRandomFactUseCase,
    private val mapper: FactDetailsDomainToUiMapper,
    private val factCache: FactCache
) : ViewModel() {
    fun fetchFact(number: Int) {
        communication.map(FactElementUi.Progress)
        viewModelScope.launch(dispatchersIO) {
            val factDomain = fetchFactUseCase.execute(number)
            val factUi = factDomain.map(mapper)
            withContext(dispatchersMain) {
                factUi.map(communication)
            }
        }
    }

    fun fetchRandomFact() {
        communication.map(FactElementUi.Progress)
        viewModelScope.launch(dispatchersIO) {
            val factDomain = fetchRandomFactUseCase.execute()
            val factUi = factDomain.map(mapper)
            withContext(dispatchersMain) {
                factUi.map(communication)
            }
        }
    }

    fun getFactElementUiLiveData() = communication.getLiveData()

    fun getNumber() = factCache.readFactInfo().first
    fun getFact() = factCache.readFactInfo().second
}