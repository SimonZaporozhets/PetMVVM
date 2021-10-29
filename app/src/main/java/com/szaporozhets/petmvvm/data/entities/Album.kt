package com.szaporozhets.petmvvm.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String
)
