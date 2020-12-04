package com.timlam.domain.repositories

interface ColorsRepository {

    fun getColor(previousColor: Int): Int

}
