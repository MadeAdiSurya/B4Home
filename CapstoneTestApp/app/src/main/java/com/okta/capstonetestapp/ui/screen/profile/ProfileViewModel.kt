package com.okta.capstonetestapp.ui.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel(){
    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> get() = _email

    init {
        val user = Firebase.auth.currentUser
        _email.value = user?.email ?: ""
    }
}