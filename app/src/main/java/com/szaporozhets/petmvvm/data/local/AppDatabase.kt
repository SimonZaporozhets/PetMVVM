package com.szaporozhets.petmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.szaporozhets.petmvvm.data.entities.Album
import com.szaporozhets.petmvvm.data.entities.Photo
import com.szaporozhets.petmvvm.data.entities.User

@Database(entities = [User::class, Album::class, Photo::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "users")
                .fallbackToDestructiveMigration()
                .build()
    }

}