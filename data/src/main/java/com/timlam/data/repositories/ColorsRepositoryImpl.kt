package com.timlam.data.repositories

import com.timlam.domain.models.AwesomeColor
import com.timlam.domain.repositories.ColorsRepository
import kotlin.random.Random

class ColorsRepositoryImpl : ColorsRepository {

    companion object {

        private const val MINIMUM_COLOR_NUMBER = 0
        private const val MAXIMUM_COLOR_NUMBER = 10

    }

    override fun getColor(previousColor: Int): AwesomeColor = AwesomeColor(color = getRandomColor(previousColor))

    private fun getRandomColor(previousNumber: Int): Int {
        var nextNumber = Random.nextInt(MINIMUM_COLOR_NUMBER, MAXIMUM_COLOR_NUMBER)

        while (nextNumber == previousNumber) {
            nextNumber = Random.nextInt(MINIMUM_COLOR_NUMBER, MAXIMUM_COLOR_NUMBER)
        }

        return nextNumber
    }
}
