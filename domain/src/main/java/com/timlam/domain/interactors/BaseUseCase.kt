package com.timlam.domain.interactors

interface BaseUseCase<T : Any, R : Any> {

    suspend operator fun invoke(param: T): R

}
