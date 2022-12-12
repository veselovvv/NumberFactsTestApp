package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.data.facts.FactData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchFactsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val facts = listOf(
            FactData(1, "1 is the loneliest number."),
            FactData(10, "10 is the number of Provinces in Canada."),
            FactData(99, "99 is the highest jersey number allowed in most major league sports.")
        )

        val factMapper = BaseFactDataToDomainMapper()
        val useCase = FetchFactsUseCase(TestFactsRepository(), BaseFactsDataToDomainMapper(factMapper))

        val expected = FactsDomain.Success(facts, factMapper)
        val actual = useCase.execute()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val factMapper = BaseFactDataToDomainMapper()
        val useCase = FetchFactsUseCase(
            TestFactsRepository(Exception()),
            BaseFactsDataToDomainMapper(factMapper)
        )

        val expected = FactsDomain.Fail(ErrorType.GENERIC_ERROR)
        val actual = useCase.execute()
        assertEquals(expected, actual)
    }
}