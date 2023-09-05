//package com.example.multi_platform_android_project
//
//@ExperimentalCoroutinesApi
//class AppViewModel: ViewModel() {
//    private val viewModelScope = CoroutineScope(Dispatchers.Main)
//    private val repo = MovieRepository()
//    val searchData: MutableState<DataState<BaseModelV2>?> = mutableStateOf(null)
//    @ExperimentalCoroutinesApi
//    @FlowPreview
//    fun searchApi(searchKey: String) {
//        viewModelScope.launch {
//            flowOf(searchKey).debounce(300)
//                .filter {
//                    it.trim().isEmpty().not()
//                }
//                .distinctUntilChanged()
//                .flatMapLatest {
//                    repo.searchMovie(it)
//                }.collect {
//                    if (it is DataState.Success){
//                        it.data
//                    }
//                    searchData.value = it
//                }
//        }
//    }
//}