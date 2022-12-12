package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.data.facts.FactData
import com.veselovvv.numberfactstestapp.data.facts.FactsData
import com.veselovvv.numberfactstestapp.data.facts.FactsRepository

class TestFactsRepository(private val exception: Exception? = null) : FactsRepository {
    override suspend fun fetchFacts() =
        if (exception == null)
            FactsData.Success(
                listOf(
                    FactData(1, "1 is the loneliest number."),
                    FactData(10, "10 is the number of Provinces in Canada."),
                    FactData(99, "99 is the highest jersey number allowed in most major league sports.")
                )
            )
        else FactsData.Fail(exception)

    override suspend fun deleteFacts() =
        if (exception == null)
            FactsData.Success(listOf())
        else FactsData.Fail(exception)
}