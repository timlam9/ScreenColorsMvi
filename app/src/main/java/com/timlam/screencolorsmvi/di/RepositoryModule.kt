package com.timlam.screencolorsmvi.di

import com.timlam.data.ColorsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideColorsRepository(): ColorsRepository = ColorsRepository()

}
