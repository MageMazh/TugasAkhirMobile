package com.D121211069.catpedia.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211069.catpedia.R
import com.D121211069.catpedia.data.model.Cat
import com.D121211069.catpedia.ui.component.BottomBar
import com.D121211069.catpedia.ui.theme.poppinsFontFamily
import com.D121211069.catpedia.ui.viewmodel.CatpediaViewModel
import com.D121211069.catpedia.ui.viewmodel.MainUiState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(viewModel: CatpediaViewModel, navController: NavHostController) {
    viewModel.getCats()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        Column() {
            Text(
                text = "Welcome to Catpedia",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 15.dp, start = 10.dp),
                fontSize = 28.sp,
                lineHeight = 28.sp,
            )
            Text(
                text = "Witness the beauty of the world of cats through a variety of different types and colors. Find various interesting information and unique characteristics of various types of cats here.",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 15.dp, start = 10.dp, end = 10.dp),
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            )
            Column(modifier = Modifier.padding(it)) {
                ListCatsScreen(viewModel.mainUiState, navController)
            }
        }
    }
}


@Composable
private fun ListCatsScreen(mainUiState: MainUiState, navController: NavController) {
    when (mainUiState) {
        is MainUiState.Success -> ListCats(mainUiState.items, modifier = Modifier, navController)
        is MainUiState.Error -> Error()
        is MainUiState.Loading -> Loading()
    }

}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,
    ) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.cat_sleep),
            contentDescription = "sleeping cat"
        )
        Text(
            text = "An error appears, please try again or contact us",
            modifier = modifier.padding(horizontal = 12.dp),
            textAlign = TextAlign.Justify,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color.Blue,
            modifier = Modifier
                .scale(1.5f)
                .padding(bottom = 20.dp),
        )

        Text(
            text = "Loading...",
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
private fun ListCats(
    items: List<Cat>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = items, key = { item -> item.id }) { data ->
            CatsCard(
                data,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                navController,
            )
        }
    }
}

@Composable
private fun CatsCard(data: Cat, modifier: Modifier = Modifier, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                navController.navigate("detail/${data.id}")
            },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("https://cdn2.thecatapi.com/images/${data.referenceImageId}.jpg")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Cat image",
                    placeholder = painterResource(R.drawable.loading_icon),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .height(200.dp)
                        .width(150.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(175.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0x99000000)
                                )
                            )
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = data.name,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}
