package com.application.capstone.care.ui.screens.login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.capstone.care.api.response.LoginRequest
import com.application.capstone.care.api.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(context: Context) : ViewModel() {
    private val _email = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    private val _emailError = mutableStateOf<String?>(null)
    private val _passwordError = mutableStateOf<String?>(null)

    private val preferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val email: State<String> = _email
    val password: State<String> = _password
    val loginState: StateFlow<LoginState> = _loginState
    val emailError: State<String?> = _emailError
    val passwordError: State<String?> = _passwordError

    private val TAG = "LoginViewModel"

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _emailError.value = if (isValidEmail(newEmail)) null else "Invalid email format"
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _passwordError.value = if (newPassword.length >= 6) null else "Password must be at least 6 characters"
    }

    fun login() {
        if (validateInputs()) {
            viewModelScope.launch {
                _loginState.value = LoginState.Loading
                try {
                    val apiService = ApiConfig.getApiService("")
                    val response = apiService.login(LoginRequest(_email.value, _password.value))
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val accessToken = responseBody?.access_token
                        Log.d(TAG, "Login successful: $response")
                        Log.d(TAG, "Access Token: $accessToken")
                        _loginState.value = LoginState.Success(response.body()?.access_token ?: "")
                        accessToken?.let {
                            saveAccessToken(it)
                            _loginState.value = LoginState.Success(it)
                        }
                    } else {
                        handleErrorResponse(response.code())
                    }
                } catch (e: Exception) {
                    if (e is HttpException) {
                        handleErrorResponse(e.code())
                    } else {
                        _loginState.value = LoginState.Error("Failed to login: ${e.localizedMessage}")
                    }
                }
            }
        }
    }

    private fun saveAccessToken(token: String) {
        val editor = preferences.edit()
        editor.putString("access_token", token)
        editor.apply()
    }

    fun getAccessToken(): String? {
        return preferences.getString("access_token", null)
    }

    private fun validateInputs(): Boolean {
        val isEmailValid = isValidEmail(_email.value)
        val isPasswordValid = _password.value.length >= 6

        _emailError.value = if (isEmailValid) null else "Invalid email format"
        _passwordError.value = if (isPasswordValid) null else "Password must be at least 6 characters"

        return isEmailValid && isPasswordValid
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun handleErrorResponse(code: Int) {
        when (code) {
            400 -> _loginState.value = LoginState.Error("Invalid email or password")
            401 -> _loginState.value = LoginState.Error("Unauthorized access")
            else -> _loginState.value = LoginState.Error("An unknown error occurred")
        }
    }
}

sealed class LoginState {
    data class Error(val message: String) : LoginState()
    data class Success(val token: String) : LoginState()
    object Loading : LoginState()
    object Idle : LoginState()
}
