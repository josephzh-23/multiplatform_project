package com.example.multi_platform_android_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


// This will be the register page here and we will have many files in here

// These are some of the imorgtant code that we can have here
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalResourceApi::class
)
@Composable
fun RegisterPage() {


    val scrollState = rememberScrollState()
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {

//            Image(painterResource(MR.image), contentDescription = null)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .padding(10.dp)
        ) {
            // THis is a bit new as said
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Sign In",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.padding(20.dp))

                var text by rememberSaveable { mutableStateOf("") }



                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    OutlinedTextField(
                        value = emailValue.value,
                        onValueChange = { emailValue.value = it },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )

                    OutlinedTextField(
                        value = passwordValue.value,
                        onValueChange = { passwordValue.value = it },
//                        trailingIcon = {
//                            IconButton(onClick = {
//                                passwordVisibility.value = !passwordVisibility.value
//                            }) {
//                                Icon(
//                                    imageVector = painterResource(MR.images.password-eyes),
//                                    tint = if (passwordVisibility.value) primaryColor else Color.Gray
//                                )
//                            }
//                        },
                        label = { Text("Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester),
//                        onImeActionPerformed = { _, controller ->
//                            controller?.hideSoftwareKeyboard()
//                        }

                    )

                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(text = "Sign In", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        text = "Create An Account",
//                        modifier = Modifier.clickable(onClick = {
//                            navController.navigate("register_page"){
//                                popUpTo = navController.graph.startDestination
//                                launchSingleTop = true
//                            }
//                        })
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                }


            }
        }

    }
}
