package com.veselovvv.numberfactstestapp.domain.facts

import com.veselovvv.numberfactstestapp.core.ErrorType
import com.veselovvv.numberfactstestapp.data.facts.FactData
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseFactsDataToDomainMapperTest {
    private val factMapper = BaseFactDataToDomainMapper()
    private val mapper = BaseFactsDataToDomainMapper(factMapper)

    @Test
    fun test_success() {
        val facts = listOf(
            FactData(1, "1 is the loneliest number."),
            FactData(10, "10 is the number of Provinces in Canada."),
            FactData(99, "99 is the highest jersey number allowed in most major league sports.")
        )

        val expected = FactsDomain.Success(facts, factMapper)
        val actual = mapper.map(facts)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val expected = FactsDomain.Fail(ErrorType.GENERIC_ERROR)
        val actual = mapper.map(Exception())
        assertEquals(expected, actual)
    }
}