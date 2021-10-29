package com.szaporozhets.petmvvm.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.szaporozhets.petmvvm.data.entities.Album
import com.szaporozhets.petmvvm.data.entities.Photo
import com.szaporozhets.petmvvm.data.repository.UserRepository
import com.szaporozhets.petmvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _photos = _id.switchMap { id ->
        repository.getPhotos(id)
    }

    val photos: LiveData<Resource<List<Photo>>> = _photos


    fun start(id: Int) {
        _id.value = id
    }

}