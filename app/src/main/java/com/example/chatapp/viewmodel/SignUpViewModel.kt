package com.example.chatapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.model.Birthday
import com.example.chatapp.model.Gender
import com.example.chatapp.model.response.AccountResponse
import com.example.chatapp.service.RetrofitUtils
import com.example.chatapp.service.SignUpApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpViewModel : ViewModel() {

    val signUpApi = RetrofitUtils.createRetrofit(RetrofitUtils.BASE_API, SignUpApi::class.java)

    private var username: MutableLiveData<String> = MutableLiveData("")
    private var password: MutableLiveData<String> = MutableLiveData("")
    private var gender: MutableLiveData<Gender> = MutableLiveData(Gender.other)
    private var birthday: MutableLiveData<Birthday> = MutableLiveData(Birthday(1, 1, 2001))


    private var usernameStatus: MutableLiveData<Int> = MutableLiveData()

    val usernameStatusLive: LiveData<Int> = usernameStatus
    val signInStatusLive: MutableLiveData<Int> = MutableLiveData()
    val usernameLive: LiveData<String> = username
    val passwordLive: LiveData<String> = password
    val genderLive: LiveData<Gender> = gender
    val birthdayLive: LiveData<Birthday> = birthday


    fun setName(name: String) {
        username.value = name
    }

    fun setGender(gender: Gender) {
        this.gender.value = gender
    }

    fun setBirthday(birthday: Birthday) {
        this.birthday.value = birthday
    }

    fun setPassword(password: String) {
        this.password.value = password
    }

    fun checkAccountExist(username: String) {
        signUpApi.checkAccountExist(username).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                usernameStatus.value=it.status
            }, {})
    }

    fun signUp(username: String, password: String) {
        signUpApi.signUp(username, password).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            signInStatusLive.value=it.status
            }, {})
    }

}