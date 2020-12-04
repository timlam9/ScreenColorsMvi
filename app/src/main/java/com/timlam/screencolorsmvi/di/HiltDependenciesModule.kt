package com.timlam.screencolorsmvi.di

import com.timlam.data.repositories.ColorsRepositoryImpl
import com.timlam.domain.interactors.GetColorUseCase
import com.timlam.domain.interactors.GetColorUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HiltDependenciesModule {

    @Provides
    @Singleton
    fun provideColorsRepository(): ColorsRepositoryImpl =
        ColorsRepositoryImpl()

    @Provides
    fun getColorUseCase(): GetColorUseCase =
        GetColorUseCaseImpl(provideColorsRepository())

}
