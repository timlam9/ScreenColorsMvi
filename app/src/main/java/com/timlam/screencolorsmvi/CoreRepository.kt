package com.timlam.screencolorsmvi

import kotlin.random.Random

class CoreRepository {

    fun getColor(previousColor: Int): Int = getRandomColor(previousColor)

    private fun getRandomColor(previousNumber: Int): Int {
        var nextNumber = Random.nextInt(0, 6)

        while (nextNumber == previousNumber) {
            nextNumber = Random.nextInt(0, 6)
        }
        return nextNumber
    }

}
