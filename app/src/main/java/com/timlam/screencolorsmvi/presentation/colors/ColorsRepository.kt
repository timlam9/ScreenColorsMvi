package com.timlam.screencolorsmvi.presentation.colors

import kotlin.random.Random

class ColorsRepository {

    fun getColor(previousColor: Int): Int = getRandomColor(previousColor)

    private fun getRandomColor(previousNumber: Int): Int {
        var nextNumber = Random.nextInt(0, 6)

        while (nextNumber == previousNumber) {
            nextNumber = Random.nextInt(0, 6)
        }
        return nextNumber
    }

}
