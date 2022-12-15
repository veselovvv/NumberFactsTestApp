package com.veselovvv.numberfactstestapp.data.fact.cloud

import com.veselovvv.numberfactstestapp.data.facts.FactData
import com.veselovvv.numberfactstestapp.data.facts.ToFactMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class FactCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = FactCloudMapper.Base(ToFactMapper.Base())
        val expected = FactData(1, "1 is the loneliest number.")
        val actual = mapper.map(FactCloud("1 is the loneliest number."))
        assertEquals(expected, actual)
    }
}