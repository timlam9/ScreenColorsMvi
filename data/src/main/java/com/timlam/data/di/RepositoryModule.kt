package com.timlam.data.di

import com.timlam.data.repositories.ColorsRepositoryImpl
import com.timlam.domain.repositories.ColorsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ColorsRepository> { ColorsRepositoryImpl() }
}
