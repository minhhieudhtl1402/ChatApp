package com.example.chatapp.model.response

data class ChatMessageResponse(val content: String = "", val type: Int) {
    companion object {
        const val TYPE_SEND = 0
        const val TYPE_RECEIVE = 1
    }

}
