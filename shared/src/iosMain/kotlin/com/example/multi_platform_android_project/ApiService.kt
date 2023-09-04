package com.example.multi_platform_android_project

import io.ktor.client.HttpClient
import io.ktor.client.engine.ios.Ios
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
actual class ApiService {
    actual fun build(): HttpClient {
        return HttpClient(Ios) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}