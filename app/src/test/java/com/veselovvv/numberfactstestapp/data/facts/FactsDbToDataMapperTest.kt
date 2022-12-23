package com.veselovvv.numberfactstestapp.data.facts

import com.veselovvv.numberfactstestapp.data.core.FactDb
import org.junit.Assert.assertEquals
import org.junit.Test

class FactsDbToDataMapperTest {
    @Test
    fun test_mapping() {
        val mapper = FactsDbToDataMapper.Base(ToFactMapper.Base())
        val expected = listOf(
            FactData(99, "99 is the highest jersey number allowed in most major league sports."),
            FactData(1, "1 is the loneliest number."),
            FactData(10, "10 is the number of Provinces in Canada.")
        )
        val actual = mapper.map(
            listOf(
                FactDb(
                    99,
                    "99 is the highest jersey number allowed in most major league sports.",
                    "20231221173000"
                ),
                FactDb(
                    1,
                    "1 is the loneliest number.",
                    "20221221173000"
                ),
                FactDb(
                    10,
                    "10 is the number of Provinces in Canada.",
                    "20220921173012"
                )
            )
        )
        assertEquals(expected, actual)
    }
}