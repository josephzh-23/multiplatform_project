package com.example.multi_platform_android_project.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multi_platform_android_project.Greeting
import com.example.multi_platform_android_project.RegisterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.multi_platform_android_project.Helper.update
import com.example.multi_platform_android_project.MainView
import com.example.multi_platform_android_project.Registration.RegistrationFormEvent
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Becareful of the setcontent that's used here
        setContent {
            MainView()
        }
    }
}


@Composable
fun RegisterScreen() {


    val viewModel = RegisterViewModel()

    val value by viewModel.state.collectAsState()
    with(viewModel.state) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val context = LocalContext.current
            LaunchedEffect(key1 = context) {
                viewModel.validationEvents.collect { event ->
                    when (event) {
                        is RegisterViewModel.ValidationEvent.Success -> {
                            Toast.makeText(
                                context,
                                "Registration successful",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = value.email,
                    onValueChange = {str->
                       update{it.copy(email = str)}
                    },
                    isError = value.emailError != null,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Email")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done

                    )
                )


                if (value.emailError != null) {
                    Text(
                        text = value.emailError!!,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = value.password,
                    onValueChange = { str ->
                        update { it.copy(password = str) }

                    },
                    isError = value.passwordError != null,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Password")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                        , imeAction = ImeAction.Done

                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                if (value.passwordError != null) {
                    Text(
                        text = value.passwordError!!,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = value.repeatedPassword,
                    onValueChange = { str ->
                        update { it.copy(repeatedPassword = str) }

                    },
                    isError = value.repeatedPasswordError != null,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Repeat password")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                if (value.repeatedPasswordError != null) {
                    Text(
                        text = value.repeatedPasswordError!!,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = value.acceptedTerms,
                        onCheckedChange = {
                            update{it.copy(acceptedTerms =  true)}
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Accept terms")
                }
                if (value.termsError != null) {
                    Text(
                        text = value.termsError!!,
                        color = MaterialTheme.colors.error,
                    )
                }

                Button(
                    onClick = {
                        viewModel.onEvent(RegistrationFormEvent.Submit)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Register user here")
                }
            }
        }
    }
}



@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
