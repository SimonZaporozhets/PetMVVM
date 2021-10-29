package com.szaporozhets.petmvvm.data.remote

import com.szaporozhets.petmvvm.data.entities.Album
import com.szaporozhets.petmvvm.data.entities.Photo
import com.szaporozhets.petmvvm.data.entities.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users")
    suspend fun getAllUsers() : Response<List<User>>

    @GET("users/{id}/albums")
    suspend fun getAlbumsByUserId(@Path("id") id: Int): Response<List<Album>>

    @GET("albums/{id}/photos")
    suspend fun getPhotosByAlbumId(@Path("id") id: Int): Response<List<Photo>>
}