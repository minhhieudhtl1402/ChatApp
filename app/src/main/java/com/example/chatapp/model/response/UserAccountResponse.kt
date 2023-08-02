package com.example.chatapp.model.response

import java.sql.Timestamp

data class UserAccountResponse(val id: Int = 0, val name: String = "", val createAt: Timestamp)
