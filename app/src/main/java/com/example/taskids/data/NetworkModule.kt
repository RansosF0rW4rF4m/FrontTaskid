package com.example.taskids.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return ApiService()
    }

    @Singleton
    @Provides
    fun provideUserService(apiService: ApiService): UserService {
        return apiService.retrofit.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideTaskService(apiService: ApiService): TaskService {
        return apiService.retrofit.create(TaskService::class.java)
    }
}
