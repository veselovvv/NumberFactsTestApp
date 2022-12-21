package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.data.fact.FactDetailsDataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseFactDetailsDataToDomainMapper : FactDetailsDataToDomainMapper {
    override fun map() = FactDetailsDomain.Success

    override fun map(exception: Exception) = FactDetailsDomain.Fail(
        when (exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}