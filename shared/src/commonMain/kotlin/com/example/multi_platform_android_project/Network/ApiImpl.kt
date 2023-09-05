package com.example.multi_platform_android_project.Network

import Student
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get

class ApiImpl {


    // API for returnining students here

    // And then using the cdoe here we could ahve the code for doing the above here
    internal suspend fun getStudents(
        page: Int
    ): List<Student> {

        //
//        return client.get {
//            getStudents(1)
//        }.body()

        // use mock data for now
        return listOf(
            Student(
                1, "joe", "josephzh23@hotmail.com", "science",
                2, ""
            )
        )
    }

    private suspend fun HttpRequestBuilder.getStudents(
        page: Int
    ) {
        url {
//            encodedPath = "3/movie/popular"
//            parameters.append("page", page.toString())
        }
    }

}
