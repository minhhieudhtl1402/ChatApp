package com.example.chatapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private lateinit var usernameLiveData: MutableLiveData<String>
    private lateinit var passwordLiveData: MutableLiveData<String>
    private lateinit var birthdayLiveData: MutableLiveData<String>
    private lateinit var genderLiveData: MutableLiveData<String>

    init {
        usernameLiveData.value = ""
        passwordLiveData.value = ""
        birthdayLiveData.value = ""
        genderLiveData.value = ""
    }

    public fun getUsername(): String {
        return usernameLiveData.value.toString()
    }

    public fun getPassword(): String {
        return passwordLiveData.value.toString()
    }

    public fun getBirthday(): String {
        return birthdayLiveData.value.toString()
    }

    public fun getGender(): String {
        return genderLiveData.value.toString()
    }

    public fun setUsername(name: String) {
        usernameLiveData.value = name
    }

    public fun setPassword(password: String) {
        passwordLiveData.value = password
    }


}