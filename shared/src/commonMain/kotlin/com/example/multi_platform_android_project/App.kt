package com.example.multi_platform_android_project

import HomeScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.multi_platform_android_project.Network.MainPageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.navigation.BackHandler
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App(viewModel: MainPageViewModel = MainPageViewModel()) {
    val isAppBarVisible = remember { mutableStateOf(true) }
    val searchProgressBar = remember { mutableStateOf(false) }
    val navigator = rememberNavigator()

    BackHandler(isAppBarVisible.value.not()) {
        isAppBarVisible.value = true
    }

    MaterialTheme{
        Scaffold(topBar = {
            if (isAppBarVisible.value.not()) {
            }
        }, bottomBar = {

            when (currentRoute(navigator)) {
                NavigationScreen.Home.route-> {
                    BottomNavigationUI(navigator)
                }
            }
        }) {
            Navigation(navigator)

        }
    }
}


@Composable
fun Navigation(navigator: Navigator) {
    NavHost(
        navigator = navigator,
        initialRoute = NavigationScreen.Home.route,
    ) {
        scene(route = NavigationScreen.Home.route) {
            HomeScreen(navigator)
        }

    }
}
@Composable
fun BottomNavigationUI(navigator: Navigator) {
    NavigationBar {
        val items = listOf(
            NavigationScreen.HomeNav,
        )
        items.forEach {
            NavigationBarItem(label = { Text(text = it.title) },
                selected = it.route == currentRoute(navigator),
                icon = it.navIcon,
                onClick = {
                    navigator.navigate(
                        it.route,
                        NavOptions(
                            launchSingleTop = true,
                        ),
                    )
                })
        }
    }
}

@Composable
fun isBackButtonEnable(navigator: Navigator): Boolean {
    return when (currentRoute(navigator)) {
        NavigationScreen.Home.route-> {
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun currentRoute(navigator: Navigator): String? {
    return navigator.currentEntry.collectAsState(null).value?.route?.route

}