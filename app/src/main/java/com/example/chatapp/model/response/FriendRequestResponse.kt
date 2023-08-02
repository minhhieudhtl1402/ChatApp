package com.example.chatapp.model.response

data class FriendRequestResponse(val message: String, val status: Int) {
    companion object {
        const val SEND_REQUEST_SUCCESSFUL = 20200
        const val SEND_REQUEST_FAILED = 20400
        const val USERNAME_IS_NOT_EXIST = 20700
        const val ACCEPT_REQUEST_SUCCESSFUL = 20500
        const val REFUSE_REQUEST_SUCCESSFUL = 20600
        const val CANCEL_SENDING_REQUEST_SUCCESSFUL = 20800
        const val CANCEL_SENDING_REQUEST_FALSE = 20900
    }

}
