package com.example.chatapp.model

import android.app.Application

class GlobalContext : Application() {
    private var name: String = ""
    private var email: String = ""

    fun getName(): String {
        return name
    }

    fun getEmail(): String {
        return email
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setEmail(email: String) {
        this.email = email
    }
}