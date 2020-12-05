package com.timlam.domain.repositories

import com.timlam.domain.models.AwesomeColor

interface ColorsRepository {

    fun getColor(previousColor: Int): AwesomeColor

}
