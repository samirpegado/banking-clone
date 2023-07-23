package com.example.bankclone.data

sealed class LoginButtonAction {
    data class Number(val number: Int): LoginButtonAction()
    object Clear: LoginButtonAction()
}

