package com.tessafold.social.dependencyinjection.module

import com.tessafold.social.models.manager.DataRepository
import com.tessafold.social.models.manager.DataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): DataSource
}