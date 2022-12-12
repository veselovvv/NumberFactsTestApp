package com.veselovvv.numberfactstestapp.data.facts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FactDb(
    @PrimaryKey val number: Int, // TODO make bigger?
    val fact: String
) {
    fun map(mapper: ToFactMapper) = mapper.map(number, fact)
}
