package com.pg.salud.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
}

class AppModule {

}