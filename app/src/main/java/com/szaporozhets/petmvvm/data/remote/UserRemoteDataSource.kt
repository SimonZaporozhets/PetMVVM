package com.szaporozhets.petmvvm.data.remote

import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
) : BaseDataSource() {
    suspend fun getUsers() = getResult { userService.getAllUsers() }

    suspend fun getAlbums(id: Int) = getResult { userService.getAlbumsByUserId(id) }

    suspend fun getPhotos(id: Int) = getResult { userService.getPhotosByAlbumId(id) }
}