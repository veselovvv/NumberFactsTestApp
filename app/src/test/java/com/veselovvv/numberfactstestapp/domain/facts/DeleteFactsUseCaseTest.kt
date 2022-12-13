package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class DeleteFactsUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val factMapper = BaseFactDataToDomainMapper()
        val useCase = DeleteFactsUseCase.Base(
            TestFactsRepository(),
            BaseFactsDataToDomainMapper(factMapper)
        )

        val expected = FactsDomain.Success(listOf(), factMapper)
        val actual = useCase.execute()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val factMapper = BaseFactDataToDomainMapper()
        val useCase = DeleteFactsUseCase.Base(
            TestFactsRepository(Exception()),
            BaseFactsDataToDomainMapper(factMapper)
        )

        val expected = FactsDomain.Fail(ErrorType.GENERIC_ERROR)
        val actual = useCase.execute()
        Assert.assertEquals(expected, actual)
    }
}