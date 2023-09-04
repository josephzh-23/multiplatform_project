package com.example.multi_platform_android_project.Network

import io.ktor.client.HttpClient


/*
Will work on figuring this out

1.
 */

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class ApiService() {
    fun build(): HttpClient
}