package com.example.multi_platform_android_project.Login

import DataState
import Student
import androidx.compose.runtime.collectAsState
import com.example.multi_platform_android_project.Helper.mapError
import com.example.multi_platform_android_project.Helper.mapToHttpError
import com.example.multi_platform_android_project.Helper.singleFlowOf
import com.example.multi_platform_android_project.Registration.RegistrationFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope


class LoginViewModel(): ViewModel(){

    private var _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun login(userName: String?, password: String?) {
        //Todo
    }

    data class LoginState(
        val loading: Boolean = false,
        val userName: String? = null,
        val password: String? = null
    )
}