package com.example.multi_platform_android_project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform