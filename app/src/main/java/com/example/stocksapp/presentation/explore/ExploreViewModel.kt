package com.example.stocksapp.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocksapp.db.entity.SearchKeywordEntity
import com.example.stocksapp.repository.Repository
import com.example.stocksapp.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private val _stocksData =
        MutableStateFlow<ResponseModel<ExploreScreenData>>(ResponseModel.Loading)
    val stocksData: StateFlow<ResponseModel<ExploreScreenData>> = _stocksData

    private val _recentKeywords =
        MutableStateFlow<List<SearchKeywordEntity>>(emptyList())
    val recentKeywords: StateFlow<List<SearchKeywordEntity>> = _recentKeywords

    init {
        getData()
        getSearchKeywords()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getExploreScreenData().collect {
                _stocksData.value = it
            }
        }
    }

    fun getSearchKeywords() {
        viewModelScope.launch(Dispatchers.IO) {
           repo.getRecentKeywords()
               .collectLatest {  _recentKeywords.value = it}
        }
    }
}