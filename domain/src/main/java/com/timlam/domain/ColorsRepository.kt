package com.timlam.domain

interface ColorsRepository {

    fun getColor(previousColor: Int): Int

}
