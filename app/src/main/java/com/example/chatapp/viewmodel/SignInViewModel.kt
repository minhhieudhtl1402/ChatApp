package com.example.chatapp.viewmodel

import android.app.ApplicationErrorReport.AnrInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.model.response.ChatMessageResponse
import com.example.chatapp.model.response.ChatResponse
import com.example.chatapp.model.response.FriendRequestResponse
import com.example.chatapp.model.response.SendingRequestResponse
import com.example.chatapp.model.response.UserAccountResponse
import com.example.chatapp.service.RetrofitUtils
import com.example.chatapp.service.SignInApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignInViewModel : ViewModel() {
    private var id: MutableLiveData<Int> = MutableLiveData()
    private var status: MutableLiveData<Int> = MutableLiveData()
    private var username: MutableLiveData<String> = MutableLiveData("")
    private var password: MutableLiveData<String> = MutableLiveData("")
    var chatList: MutableLiveData<ArrayList<ChatResponse>> = MutableLiveData()

    var chatListLive: LiveData<ArrayList<ChatResponse>> = chatList
    var chatListCount: MutableLiveData<Int> = MutableLiveData(0)
    var currentReceiveId: MutableLiveData<Int> = MutableLiveData(0)

    var idLive: LiveData<Int> = id
    val statusLive: LiveData<Int> = status
    val usernameLive: LiveData<String> = username
    val passwordLive: LiveData<String> = password

    var receiveId: MutableLiveData<Int> = MutableLiveData(0)
    var receiveName: MutableLiveData<String> = MutableLiveData("")
    var messages: MutableLiveData<ArrayList<ChatMessageResponse>> = MutableLiveData()
    var friendRequest: MutableLiveData<ArrayList<UserAccountResponse>> = MutableLiveData()
    val sendingRequestResponse: MutableLiveData<ArrayList<SendingRequestResponse>> =
        MutableLiveData()
    var friendResponse: MutableLiveData<FriendRequestResponse> = MutableLiveData()

    var cancelSendingRequest: MutableLiveData<Int> = MutableLiveData()

    private val signInApi =
        RetrofitUtils.createRetrofit(RetrofitUtils.BASE_API, SignInApi::class.java)

    fun signIn(username: String, password: String) {
        signInApi.signIn(username, password).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                status.value = it.status
                id.value = it.userAccount.id
                this.username.value = it.userAccount.username
                this.password.value = it.userAccount.password
                //lang nghe su thay doi va gan !!
            }, {})
    }

    fun getChatList() {
        signInApi.getChatList(id.value!!).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                chatList.value = it
                chatListCount.value = it.size
            }, {})
    }

    fun getChatDetailList() {
        signInApi.getChatDetailList(id.value!!, receiveId.value!!)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                messages.value = it
            }, {})
    }

    fun setId(id: Int) {
        this.id.value = id
    }

    fun sendNewMessage(message: String) {
        signInApi.sendNewMessage(id.value!!, receiveId.value!!, message)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getChatDetailList()
            }, {})
    }

    fun getAllFriendRequest() {
        signInApi.getFriendRequest(id.value!!)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                friendRequest.value = it
            }, {})
    }

    fun acceptFriend(requestId: Int) {
        signInApi.acceptFriendRequest(id.value!!, requestId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getChatList()
                getAllFriendRequest()
            }, {})
    }

    fun removeRequest(requestId: Int) {
        signInApi.removeFriendRequest(id.value!!, requestId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getAllFriendRequest()
            }, {})
    }

    fun requestNewFriend(requestId: Int, name: String) {
        signInApi.requestFriendWithName(requestId, name)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                friendResponse.value = it
                getAllSendingRequest()
            }, {})
    }

    fun getAllSendingRequest() {
        signInApi.getAllSendingRequest(id.value!!)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sendingRequestResponse.value = it
            }, {})
    }

    fun cancelSendingRequest(receiveId: Int) {
        signInApi.cancelSendingRequest(receiveId, id.value!!)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cancelSendingRequest.value = it.status
                getAllSendingRequest()
            })
    }

}