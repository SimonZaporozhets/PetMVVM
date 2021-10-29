package com.szaporozhets.petmvvm.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.szaporozhets.petmvvm.data.local.AppDatabase
import com.szaporozhets.petmvvm.data.local.UserDao
import com.szaporozhets.petmvvm.data.remote.UserRemoteDataSource
import com.szaporozhets.petmvvm.data.remote.UserService
import com.szaporozhets.petmvvm.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: UserService) = UserRemoteDataSource(userService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: UserRemoteDataSource,
                          localDataSource: UserDao
    ) = UserRepository(remoteDataSource, localDataSource)
}