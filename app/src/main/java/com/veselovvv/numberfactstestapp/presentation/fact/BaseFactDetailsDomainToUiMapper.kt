package com.veselovvv.numberfactstestapp.presentation.fact

import com.veselovvv.numberfactstestapp.R
import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.core.ResourceProvider
import com.veselovvv.numberfactstestapp.domain.fact.FactDetailsDomainToUiMapper

class BaseFactDetailsDomainToUiMapper(
    private val resourceProvider: ResourceProvider
) : FactDetailsDomainToUiMapper {
    override fun map() = FactDetailsUi.Success

    override fun map(error: ErrorType) = FactDetailsUi.Fail(
        resourceProvider.getString(
            when (error) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong_message
            }
        )
    )
}