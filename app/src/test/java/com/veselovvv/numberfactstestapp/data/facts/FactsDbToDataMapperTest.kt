package com.veselovvv.numberfactstestapp.data.facts

import org.junit.Assert.assertEquals
import org.junit.Test

class FactsDbToDataMapperTest {
    @Test
    fun test_mapping() {
        val mapper = FactsDbToDataMapper.Base(ToFactMapper.Base())
        val expected = listOf(
            FactData(1, "1 is the loneliest number."),
            FactData(10, "10 is the number of Provinces in Canada."),
            FactData(99, "99 is the highest jersey number allowed in most major league sports.")
        )
        val actual = mapper.map(
            listOf(
                FactDb(1, "1 is the loneliest number."),
                FactDb(10, "10 is the number of Provinces in Canada."),
                FactDb(99, "99 is the highest jersey number allowed in most major league sports.")
            )
        )
        assertEquals(expected, actual)
    }
}