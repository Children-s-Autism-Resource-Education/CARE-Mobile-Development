package com.application.capstone.care.ui.screens.features

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.capstone.care.api.retrofit.ApiConfig
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class EmotionDetectionViewModel : ViewModel() {

    private val _emotionResult = MutableLiveData<String>()
    val emotionResult: LiveData<String> = _emotionResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    fun detectEmotion(context: Context, imageUri: String) {
        viewModelScope.launch {
            val userToken = getTokenFromSharedPreferences(context)
            if (userToken.isNullOrEmpty()) {
                _error.postValue("Authentication token not found.")
                return@launch
            }

            val uri = Uri.parse(imageUri)
            val inputStream = context.contentResolver.openInputStream(uri) ?: run {
                _error.postValue("Failed to open image stream")
                return@launch
            }
            val file = File(context.cacheDir, "temp_image.jpg")
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }

            val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

            val apiService = ApiConfig.getApiService(userToken)

            try {
                val response = apiService.predictEmotion(body)
                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()!!
                    if (responseBody.status == "success") {
                        _emotionResult.postValue("Result: ${responseBody.result}, Suggestion: ${responseBody.suggestion}")
                    } else {
                        _error.postValue("API failed with message: ${responseBody.message}")
                    }
                } else {
                    _error.postValue("API call was unsuccessful: ${
                        response.errorBody()?.string() ?: "Unknown error"
                    }")
                }
            } catch (e: Exception) {
                _error.postValue("Exception during API call: ${e.localizedMessage}")
            }
        }
    }

    private fun getTokenFromSharedPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("access_token", null)
    }
}
