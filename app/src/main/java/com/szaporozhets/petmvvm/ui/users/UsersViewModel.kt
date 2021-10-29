package com.szaporozhets.petmvvm.ui.users

import androidx.lifecycle.ViewModel
import com.szaporozhets.petmvvm.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val users = repository.getUsers()

}