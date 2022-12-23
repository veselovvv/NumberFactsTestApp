package com.veselovvv.numberfactstestapp.data.core

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veselovvv.numberfactstestapp.data.facts.ToFactMapper

@Entity
data class FactDb(
    @PrimaryKey val number: Int, // TODO make bigger?
    val fact: String,
    val currentDateTime: String
) {
    fun map(mapper: ToFactMapper) = mapper.map(number, fact)
}
