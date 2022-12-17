package com.veselovvv.numberfactstestapp.domain.fact

import com.veselovvv.numberfactstestapp.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseFactDetailsDataToDomainMapperTest {
    private val mapper = BaseFactDetailsDataToDomainMapper()

    @Test
    fun test_success() {
        val expected = FactDetailsDomain.Success
        val actual = mapper.map()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = FactDetailsDomain.Fail(ErrorType.NO_CONNECTION)
        var actual = mapper.map(UnknownHostException())
        assertEquals(expected, actual)

        expected = FactDetailsDomain.Fail(ErrorType.GENERIC_ERROR)
        actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}