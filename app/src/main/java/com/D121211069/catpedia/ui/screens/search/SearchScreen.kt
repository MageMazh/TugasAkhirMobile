package com.D121211069.catpedia.ui.screens.search

import android.util.Log
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import com.D121211069.catpedia.ui.viewmodel.SearchUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: CatpediaViewModel, navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Text(
                text = "Search",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 15.dp, start = 10.dp),
                fontSize = 28.sp,
                lineHeight = 28.sp,
            )
            Text(
                text = "Use our search feature and click the search icon to find the cat breed you want to view.",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            )
            SearchBar(
                hint = "",
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .fillMaxWidth(),
                onSearch = { query ->
                    viewModel.getCatsSearch(query)
                }
            )
            Column() {
                SearchCat(viewModel.searchUiState, navController)
            }
        }
    }
}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
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
        horizontalAlignment = Alignment.CenterHorizontally
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
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(color = Color(0xFFe3f6f5), CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

        IconButton(
            onClick = {
                onSearch(text)
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun SearchCat(searchUiState: SearchUiState, navController: NavController) {
    var hasCompletedInitialSearch by remember { mutableStateOf(false) }

    when (searchUiState) {
        is SearchUiState.Success -> ListCats(
            searchUiState.items,
            modifier = Modifier,
            navController
        )

        is SearchUiState.Error -> Error()
        is SearchUiState.Loading ->
            // Tampilkan Loading hanya jika search belum selesai (status pertama kali masuk ke halaman)
            if (hasCompletedInitialSearch) {
                Loading()
            }
    }
    // Update status pencarian pertama kali saat pencarian selesai
    if (searchUiState is SearchUiState.Success && !hasCompletedInitialSearch) {
        hasCompletedInitialSearch = true
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
                        .height(75.dp)
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

