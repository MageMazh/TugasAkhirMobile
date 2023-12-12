package com.D121211069.catpedia.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211069.catpedia.R
import com.D121211069.catpedia.data.model.Cat
import com.D121211069.catpedia.ui.theme.poppinsFontFamily
import com.D121211069.catpedia.ui.viewmodel.CatpediaViewModel
import com.D121211069.catpedia.ui.viewmodel.DetailUiState

@Composable
fun DetailScreen(id: String, viewModel: CatpediaViewModel, navController: NavController) {
    val film by remember(id) {
        mutableStateOf(viewModel.getCatDetail(id))
    }

    Column() {
        DetailCatState(viewModel.detailUiState, navController)
    }
}

@Composable
private fun DetailCatState(detailUiState: DetailUiState, navController: NavController) {
    when (detailUiState) {
        is DetailUiState.Success -> DetailCat(
            detailUiState.item,
            modifier = Modifier,
            navController
        )

        is DetailUiState.Error -> Error()
        is DetailUiState.Loading -> Loading()
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
fun DetailCat(item: Cat, modifier: Modifier = Modifier, navController: NavController) {
    LazyColumn() {
        item {
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(0.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data("https://cdn2.thecatapi.com/images/${item.referenceImageId}.jpg")
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.loading_icon),
                        contentDescription = "Cat image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .paint(painter = painterResource(id = R.drawable.image_not_available)),
                    )
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(35.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.6f),
                                shape = CircleShape
                            )
                            .clickable {
                                navController.popBackStack()
                            },
                        contentAlignment = Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "arrow back",
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxSize()
                        )
                    }

                    // Box nama dan negara kucing
                    Box(
                        Modifier
                            .padding(horizontal = 20.dp)
                            .shadow(
                                elevation = 43.dp,
                                spotColor = Color(0x0D000000),
                                ambientColor = Color(0x0D000000)
                            )
                            .width(367.dp)
                            .height(105.dp)
                            .background(
                                color = Color(0xFFe3f6f5),
                                shape = RoundedCornerShape(size = 30.dp)
                            )
                            .padding(start = 22.dp, top = 15.dp, end = 22.dp, bottom = 15.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Column(
                        ) {
                            Text(
                                text = "${item.name}",
                                style = TextStyle(
                                    fontSize = 26.sp,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.Bold,
                                )
                            )
                            Text(
                                text = item.origin,
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            )

                        }

                    }
                }
            }

            // About
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 10.dp, end = 10.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_pet),
                    contentDescription = "icon pet",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(35.dp)
                        .fillMaxHeight()
                )
                Text(
                    text = "About ${item.name}",
                    modifier = Modifier
                        .padding(top = 5.dp),
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 36.sp
                    )
                )
            }

            // Mini Card
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .height(85.dp)
                        .background(
                            color = Color(0xFFe3f6f5),
                            shape = RoundedCornerShape(size = 18.dp)
                        )
                        .padding(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = "Weight",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                            )

                        )
                        Text(
                            text = "${item.weight.metric} kg",
                            style = TextStyle(
                                fontSize = 16.7.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF6246ea),
                            ),
                        )
                    }
                }
                Box(
                    Modifier
                        .weight(1f)
                        .height(85.dp)
                        .background(
                            color = Color(0xFFe3f6f5),
                            shape = RoundedCornerShape(size = 18.dp)
                        )
                        .padding(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = "Life Span",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                            )

                        )
                        Text(
                            text = "${item.lifeSpan} yrs",
                            style = TextStyle(
                                fontSize = 16.7.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF6246ea),
                            ),
                        )
                    }
                }
                Box(
                    Modifier
                        .weight(1f)
                        .height(85.dp)
                        .background(
                            color = Color(0xFFe3f6f5),
                            shape = RoundedCornerShape(size = 18.dp)
                        )
                        .padding(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = "Energy",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                        Text(
                            text = "${item.energyLevel}/5",
                            style = TextStyle(
                                fontSize = 16.7.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF6246ea),
                            ),
                        )
                    }
                }
            }

            // Description
            Text(
                text = item.description,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 8.dp, end = 16.dp, top = 16.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Justify
                )
            )

            // Temperament
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_pet_temperament),
                    contentDescription = "icon pet temperament",
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .size(35.dp)
                )
                Text(
                    text = "${item.name}'s Temperament",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 36.sp
                    )
                )
            }
            Text(
                text = item.temperament,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Justify
                )
            )

        }
    }
}
