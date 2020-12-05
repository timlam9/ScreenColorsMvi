package com.timlam.data.repositories

import com.timlam.domain.models.AwesomeColor
import com.timlam.domain.repositories.ColorsRepository
import kotlin.random.Random

class ColorsRepositoryImpl : ColorsRepository {

    override fun getColor(previousColor: Int): AwesomeColor = AwesomeColor(color = getRandomColor(previousColor))

    private fun getRandomColor(previousNumber: Int): Int {
        var nextNumber = Random.nextInt(0, 6)

        while (nextNumber == previousNumber) {
            nextNumber = Random.nextInt(0, 6)
        }
        return nextNumber
    }

}
