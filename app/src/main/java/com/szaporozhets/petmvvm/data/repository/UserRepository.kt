package com.szaporozhets.petmvvm.data.repository

import com.szaporozhets.petmvvm.data.local.UserDao
import com.szaporozhets.petmvvm.data.remote.UserRemoteDataSource
import com.szaporozhets.petmvvm.utils.performGetOperation
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserDao
) {
    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun getAlbums(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getAlbums(id) },
        networkCall = { remoteDataSource.getAlbums(id) },
        saveCallResult = { localDataSource.insertAllAlbums(it) }
    )

    fun getPhotos(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getPhotos(id) },
        networkCall = { remoteDataSource.getPhotos(id) },
        saveCallResult = { localDataSource.insertAllPhotos(it) }
    )
}