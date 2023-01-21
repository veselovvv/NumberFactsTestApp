package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.core.ErrorType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class FetchRandomFactUseCaseTest {
    @Test
    fun test_success() = runBlocking {
        val useCase = FetchRandomFactUseCase.Base(
            TestFactRepository(),
            BaseFactDetailsDataToDomainMapper()
        )

        val expected = FactDetailsDomain.Success
        val actual = useCase.execute()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        var useCase = FetchRandomFactUseCase.Base(
            TestFactRepository(UnknownHostException()),
            BaseFactDetailsDataToDomainMapper()
        )

        var expected = FactDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = useCase.execute()
        assertEquals(expected, actual)

        useCase = FetchRandomFactUseCase.Base(
            TestFactRepository(Exception()),
            BaseFactDetailsDataToDomainMapper()
        )

        expected = FactDetailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = useCase.execute()
        assertEquals(expected, actual)
    }
}