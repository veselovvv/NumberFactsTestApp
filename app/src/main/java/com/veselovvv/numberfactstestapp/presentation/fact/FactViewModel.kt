package com.veselovvv.numberfactstestapp.presentation.fact

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.numberfactstestapp.di.core.CoreDomainModule
import com.veselovvv.numberfactstestapp.domain.fact.FactDetailsDomainToUiMapper
import com.veselovvv.numberfactstestapp.domain.fact.FetchFactUseCase
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
    private val mapper: FactDetailsDomainToUiMapper
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

    fun observe(owner: LifecycleOwner, observer: Observer<FactElementUi>) =
        communication.observe(owner, observer)
}