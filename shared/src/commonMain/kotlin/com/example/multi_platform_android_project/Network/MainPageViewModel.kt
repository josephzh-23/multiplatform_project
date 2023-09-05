package com.example.multi_platform_android_project.Network

import DataState
import Student
import com.example.multi_platform_android_project.Helper.mapError
import com.example.multi_platform_android_project.Helper.mapToHttpError
import com.example.multi_platform_android_project.Helper.singleFlowOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope


class MainPageViewModel(): ViewModel(){

    val api = ApiImpl()

    // This will work with the pagination viewmodel as we discussed before

    fun getAllStudents(page: Int) =
        singleFlowOf { DataState.Success(api.getStudents(1)) }.mapError(
            Throwable::mapToHttpError)
//        emit ( DataState.Success(api.getStudents(1)) )
            .stateIn(
                viewModelScope, SharingStarted.WhileSubscribed(),
                DataState.Loading
            )

}