package com.timlam.domain.interactors

import com.timlam.domain.interactors.BaseUseCase

interface GetColorUseCase : BaseUseCase<Int, Int> {

    override suspend operator fun invoke(colorNumber: Int): Int

}
