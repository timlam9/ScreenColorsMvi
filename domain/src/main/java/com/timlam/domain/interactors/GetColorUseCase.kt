package com.timlam.domain.interactors

import com.timlam.domain.models.AwesomeColor

interface GetColorUseCase : BaseUseCase<Int, AwesomeColor> {

    override suspend operator fun invoke(colorNumber: Int): AwesomeColor

}
