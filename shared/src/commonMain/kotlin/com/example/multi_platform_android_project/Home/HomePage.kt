import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.multi_platform_android_project.Home.StudentList
import com.example.multi_platform_android_project.Network.MainPageViewModel
import moe.tlaster.precompose.navigation.Navigator


@Composable
fun HomeScreen(
    navigator: Navigator,
    viewModel: MainPageViewModel = MainPageViewModel()
) {
    LaunchedEffect(true) {

        viewModel.getAllStudents(1)
//        viewModel.nowPlayingView(1)
    }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        viewModel.getAllStudents(1).collectAsState().value.let {
            when (it) {
                is DataState.Loading -> {
                    ProgressIndicator()
                }

                is DataState.Success<List<Student>> -> {
                    StudentList(it.data) { movieId ->

                        println("the student list is ${it.data}")
//                        navigator.navigate(NavigationScreen.MovieDetail.route.plus("/$movieId"))
                    }
                }

                is DataState.Error -> {
                    Text("The exception is ${it.exception}")
                }

                else -> {}
            }
        }
    }
}


fun <T : Any> MutableState<DataState<T>?>.pagingLoadingState(isLoaded: (pagingState: Boolean) -> Unit) {
    when (this.value) {
        is DataState.Success<T> -> {
            isLoaded(false)
        }

        is DataState.Loading -> {
            isLoaded(true)
        }

        is DataState.Error -> {
            isLoaded(false)
        }

        else -> {
            isLoaded(false)
        }
    }
}

@Composable
internal fun ProgressIndicator(isVisible: Boolean = true) {
    if (isVisible) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}