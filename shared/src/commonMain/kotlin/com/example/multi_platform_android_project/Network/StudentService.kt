package com.example.multi_platform_android_project.Network

import Student
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

interface StudentService {

    suspend fun getPosts(): List<Student>



    companion object {
        fun create(): StudentService {
            return StudentServiceImpl(
                client = HttpClient() {
                    install(Logging) {
                        level = LogLevel.ALL
                    }

                    // ContentNegotiation: Serializing/deserializing the content in a specific format.
                    // Ktor supports the following formats out-of-the-box: JSON, XML, CBOR, and ProtoBuf.
                    install(ContentNegotiation) {
                        // Used for the json serizlier
                        json()
                    }
                }
            )
        }
    }
}