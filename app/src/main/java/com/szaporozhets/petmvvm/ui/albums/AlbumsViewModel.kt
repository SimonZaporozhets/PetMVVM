package com.szaporozhets.petmvvm.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.szaporozhets.petmvvm.data.entities.Album
import com.szaporozhets.petmvvm.data.repository.UserRepository
import com.szaporozhets.petmvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _albums = _id.switchMap { id ->
        repository.getAlbums(id)
    }

    val albums: LiveData<Resource<List<Album>>> = _albums


    fun start(id: Int) {
        _id.value = id
    }

}