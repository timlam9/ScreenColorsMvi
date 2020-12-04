package com.timlam.domain.interactors

import com.timlam.domain.repositories.ColorsRepository

class GetColorUseCaseImpl(private val colorsRepository: ColorsRepository) :
    GetColorUseCase {

    override suspend fun invoke(colorNumber: Int): Int = colorsRepository.getColor(colorNumber)

}
