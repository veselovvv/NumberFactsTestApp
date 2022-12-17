package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchFactUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val useCase = FetchFactUseCase.Base(
            TestFactRepository(),
            BaseFactDetailsDataToDomainMapper()
        )

        val expected = FactDetailsDomain.Success
        val actual = useCase.execute(1)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var useCase = FetchFactUseCase.Base(
            TestFactRepository(UnknownHostException()),
            BaseFactDetailsDataToDomainMapper()
        )

        var expected = FactDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute(1)
        assertEquals(expected, actual)

        useCase = FetchFactUseCase.Base(
            TestFactRepository(Exception()),
            BaseFactDetailsDataToDomainMapper()
        )

        expected = FactDetailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute(1)
        assertEquals(expected, actual)
    }
}