package com.timlam.screencolorsmvi

import com.timlam.screencolorsmvi.presentation.colors.ColorsRepository
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
