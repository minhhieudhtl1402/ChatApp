package com.example.chatapp.model.response

data class AccountResponse(
    val message: String,
    val status: Int,
    val userAccount: UserAccount
) {
    companion object {
        const val ACCOUNT_IS_EXIST = 10400
        const val ACCOUNT_IS_NOT_EXIST = 10500
        const val INCORRECT_PASSWORD = 10600
        const val LOGIN_SUCCESSFUL = 10700
        const val SIGN_UP_SUCCESSFUL = 10800
    }
}