package com.D121211069.catpedia.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.D121211069.catpedia.ui.theme.CatpediaTheme
import com.D121211069.catpedia.ui.viewmodel.CatpediaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: CatpediaViewModel = viewModel(factory = CatpediaViewModel.Factory)

            CatpediaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CatpediaApp(viewModel = viewModel)
                }
            }
        }
    }
}
