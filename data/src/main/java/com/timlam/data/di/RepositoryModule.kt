package com.timlam.data.di

import com.timlam.data.repositories.ColorsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<ColorsRepositoryImpl> { ColorsRepositoryImpl() }
}
