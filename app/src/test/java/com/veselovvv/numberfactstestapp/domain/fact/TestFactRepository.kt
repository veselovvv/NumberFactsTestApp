package com.veselovvv.numberfactstestapp.domain.fact

class TestFactRepository(private val exception: Exception? = null) : FactRepository {
    override suspend fun fetchFact(number: Int) =
        if (exception == null) FactDetailsData.Success
        else FactDetailsData.Fail(exception)
}