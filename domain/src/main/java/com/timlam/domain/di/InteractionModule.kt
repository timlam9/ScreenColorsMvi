package com.timlam.domain.di

import com.timlam.domain.interactors.GetColorUseCase
import com.timlam.domain.interactors.GetColorUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {
    factory<GetColorUseCase> { GetColorUseCaseImpl(get()) }
}
