package com.timlam.screencolorsmvi.di

import com.timlam.data.repositories.ColorsRepositoryImpl
import com.timlam.domain.interactors.GetColorUseCase
import com.timlam.domain.interactors.GetColorUseCaseImpl
import com.timlam.domain.repositories.ColorsRepository
import com.timlam.screencolorsmvi.firebase.FirestoreDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HiltDependenciesModule {

    @Provides
    @Singleton
    fun provideColorsRepository(): ColorsRepository = ColorsRepositoryImpl()

    @Provides
    fun getColorUseCase(): GetColorUseCase = GetColorUseCaseImpl(provideColorsRepository())

    @ExperimentalCoroutinesApi
    @Provides
    @Singleton
    fun getFirebaseDataSource(): FirestoreDataSource = FirestoreDataSource()

}
