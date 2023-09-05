package com.example.multi_platform_android_project.Home

import Student
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.multi_platform_android_project.Helper.rememberAsyncImagePainter
import com.example.multi_platform_android_project.Helper.shimmerBackground

@Composable
internal fun StudentList(listItems: List<Student>, onclick: (id: Int) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 10.dp),
        content = {
            items(listItems) {
                Column(
                    modifier = Modifier.padding(
                        start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp
                    )
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
//                            AppConstant.IMAGE_URL.plus(
//                                it.poster_path
//                            )
                            "https://plus.unsplash.com/premium_photo-1664391847942-f9c4562ad692?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=3332&q=80"
                        ),
                        contentDescription = it.picture,
                        modifier = Modifier.size(250.dp).cornerRadius(10).shimmerBackground(
                            RoundedCornerShape(5.dp)
                        ).clickable {
                            onclick(it.id)
                        },
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        })
}



fun Modifier.cornerRadius(radius: Int) =
    graphicsLayer(shape = RoundedCornerShape(radius.dp), clip = true)
