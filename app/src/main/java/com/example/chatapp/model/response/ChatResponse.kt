package com.example.chatapp.model.response

import java.sql.Timestamp

data class ChatResponse(
    val content: String = "",
    val createAt: String = "",
    val name: String = "",
    val receiveId: Int = 0,
    val type: String = ""
){
}