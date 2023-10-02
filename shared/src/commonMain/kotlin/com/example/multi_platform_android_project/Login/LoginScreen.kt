package com.example.multi_platform_android_project.Login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun LoginScreen(
    navigator: Navigator,
    viewModel: LoginViewModel = LoginViewModel()
) {
    val state = viewModel.state.collectAsState()

    LoginScreenContent(
        loading = state.value.loading,
        onLogin = { userName, password ->
            viewModel.login(userName, password)
        }
    )
}

@Composable
private fun LoginScreenContent(
    loading: Boolean,
    onLogin: (String?, String?) -> Unit
) {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.weight(1f))
        TextField(value = userName,
            onValueChange = {
                userName = it
            },
            label = { Text("UserName") })
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            placeholder = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }){
                    Icon(imageVector  = image, description)
                }
            }
        )
        if (!loading) {
            Button(onClick = {
                onLogin(userName, password)
            }) {
                Text("Login")
            }
        }
        Spacer(Modifier.weight(1f))
    }

}

@Composable
private fun LoginScreenPreview() {
    // Todo: Adding preview ui library to this module doesn´t work, maybe it is not the correct module
    //LoginScreenContent()
}