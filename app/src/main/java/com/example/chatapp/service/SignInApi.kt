package com.example.chatapp.service

import com.example.chatapp.model.response.AccountResponse
import com.example.chatapp.model.response.ChatMessageResponse
import com.example.chatapp.model.response.ChatResponse
import com.example.chatapp.model.response.FriendRequestResponse
import com.example.chatapp.model.response.SendMessageResponse
import com.example.chatapp.model.response.SendingRequestResponse
import com.example.chatapp.model.response.UserAccountResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface SignInApi {
    @POST("/account/login")
    fun signIn(
        @Query(value = "username") username: String,
        @Query(value = "password") password: String
    ): Observable<AccountResponse>

    @POST("/chat/chatlist")
    fun getChatList(@Query(value = "userId") userId: Int): Observable<ArrayList<ChatResponse>>

    @POST("chat/chatdetaillist")
    fun getChatDetailList(
        @Query(value = "sendId") sendId: Int,
        @Query(value = "receiveId") receiveId: Int
    ): Observable<ArrayList<ChatMessageResponse>>

    @POST("chat/sendnewmessage")
    fun sendNewMessage(
        @Query(value = "sendId") sendId: Int,
        @Query(value = "receiveId") receiveId: Int,
        @Query(value = "content") content: String
    ): Observable<SendMessageResponse>

    @POST("friend/friendrequest")
    fun getFriendRequest(
        @Query(value = "receiveId") receiveId: Int
    ): Observable<ArrayList<UserAccountResponse>>


    @POST("friend/acceptrequest")
    fun acceptFriendRequest(
        @Query(value = "receiveId") receiveId: Int,
        @Query(value = "requestId") requestId: Int
    ): Observable<FriendRequestResponse>

    @POST("friend/removerequest")
    fun removeFriendRequest(
        @Query(value = "receiveId") receiveId: Int,
        @Query(value = "requestId") requestId: Int
    ): Observable<FriendRequestResponse>

    @POST("/friend/requestFriendWithName")
    fun requestFriendWithName(
        @Query(value = "requestId") requestId: Int,
        @Query(value = "name") name: String
    ): Observable<FriendRequestResponse>


    @POST("/friend/getAllSendingRequest")
    fun getAllSendingRequest(
        @Query(value = "requestId") requestId: Int
    ): Observable<ArrayList<SendingRequestResponse>>

    @POST("/friend/cancelSendingRequest")
    fun cancelSendingRequest(
        @Query(value = "receiveId") receiveId: Int,
        @Query(value = "requestId") requestId: Int
    ): Observable<FriendRequestResponse>
}