package com.szaporozhets.petmvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.szaporozhets.petmvvm.data.entities.Album
import com.szaporozhets.petmvvm.data.entities.Photo
import com.szaporozhets.petmvvm.data.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM albums WHERE userId = :id")
    fun getAlbums(id: Int) : LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAlbums(albums: List<Album>)

    @Query("SELECT * FROM photos WHERE albumId = :id")
    fun getPhotos(id: Int) : LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhotos(photos: List<Photo>)

}