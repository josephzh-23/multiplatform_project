package com.example.multi_platform_android_project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.multi_platform_android_project.Registration.RegistrationFormEvent
import com.example.multi_platform_android_project.Registration.RegistrationFormState
import com.example.multi_platform_android_project.Registration.ValidateEmail
import com.example.multi_platform_android_project.Registration.ValidatePassword
import com.example.multi_platform_android_project.Registration.ValidateRepeatedPassword
import com.example.multi_platform_android_project.Registration.ValidateTerms
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RegisterViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
) : ViewModel() {

    var state = MutableStateFlow(RegistrationFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when(event) {
            is RegistrationFormEvent.Submit -> {
                submitData()
            }

            else -> {
                // do nothing for now
            }
        }
    }


    // When you submit the data to the backend here
    private fun submitData() {
        val emailResult = validateEmail.execute(state.value.email)
        val passwordResult = validatePassword.execute(state.value.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.value.password, state.value.repeatedPassword
        )
        val termsResult = validateTerms.execute(state.value.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

        if (hasError) {
            state.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                    repeatedPasswordError = repeatedPasswordResult.errorMessage,
                    termsError = termsResult.errorMessage
                )
            }
            return
        }

        println("the updated event value here is ${state.value}")
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}