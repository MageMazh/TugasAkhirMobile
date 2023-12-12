package com.D121211069.catpedia.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211069.catpedia.CatpediaApplication
import com.D121211069.catpedia.data.model.Cat
import com.D121211069.catpedia.data.repository.CatRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MainUiState {
    data class Success(val items: List<Cat>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

sealed interface SearchUiState {
    data class Success(val items: List<Cat>) : SearchUiState
    object Error : SearchUiState
    object Loading : SearchUiState
}

sealed interface DetailUiState {
    data class Success(val item: Cat) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}

class CatpediaViewModel(private val catRepository: CatRepository) : ViewModel() {
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    var searchUiState: SearchUiState by mutableStateOf(SearchUiState.Loading)
        private set


    fun getCats() {
        viewModelScope.launch {
            mainUiState = MainUiState.Loading
            mainUiState = try {
                MainUiState.Success(catRepository.getCats())
            } catch (e: IOException) {
                MainUiState.Error
            } catch (e: HttpException) {
                MainUiState.Error
            }
        }
    }

    fun getCatDetail(id : String) {
        viewModelScope.launch {
            detailUiState = DetailUiState.Loading
            detailUiState = try {
                DetailUiState.Success(catRepository.getCatDetail(id))
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun getCatsSearch(id : String) {
        viewModelScope.launch {
            searchUiState = SearchUiState.Loading
            searchUiState = try {
                SearchUiState.Success(catRepository.getCatsSearch(id))
            } catch (e: IOException) {
                SearchUiState.Error
            } catch (e: HttpException) {
                SearchUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CatpediaApplication)
                val catRepository = application.container.catRepository
                CatpediaViewModel(catRepository = catRepository)
            }
        }
    }
}
