package com.example.chatapp.model.response

data class SendMessageResponse(val message: String, val status: Int) {
    companion object {
        const val SEND_SUCCESSFUL: Int = 0
        const val SEND_FAILED: Int = 1
    }
}
