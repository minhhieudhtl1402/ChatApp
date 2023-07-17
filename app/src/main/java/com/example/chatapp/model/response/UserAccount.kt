package com.example.chatapp.model.response

data class UserAccount(
    val createAt: String,
    val id: Int,
    val password: String,
    val username: String
)