package com.example.bankclone.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    var state by mutableStateOf(LoginState())

    fun onAction(action: LoginButtonAction) {
        when(action) {
            is LoginButtonAction.Number -> enterNumber(action.number)
            is LoginButtonAction.Clear -> state = LoginState()
        }
    }

    private fun enterNumber(number: Int) {
        if(state.number1.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number1 = state.number1 + number
        )
        if(state.number2.length >= MAX_NUM_LENGTH){
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 4
    }
}