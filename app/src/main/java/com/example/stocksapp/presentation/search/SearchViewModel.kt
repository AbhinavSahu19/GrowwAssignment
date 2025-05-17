package com.example.stocksapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocksapp.api.responsedto.search.SearchResponseItem
import com.example.stocksapp.db.entity.SearchKeywordEntity
import com.example.stocksapp.repository.Repository
import com.example.stocksapp.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _allDbKeywords = mutableListOf<SearchKeywordEntity>()
    private val _dbKeywords = MutableStateFlow<List<SearchKeywordEntity>>(emptyList())
    val dbKeywords: StateFlow<List<SearchKeywordEntity>> = _dbKeywords

    private val _apiKeywords =
        MutableStateFlow<ResponseModel<List<SearchResponseItem>>>(ResponseModel.Loading)
    val apiKeywords: StateFlow<ResponseModel<List<SearchResponseItem>>> = _apiKeywords

    init {
        getAllDbKeywords()
    }

    fun getAllDbKeywords() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getRecentKeywords().collect {
                _allDbKeywords.addAll(it)
                _dbKeywords.value = it
            }
        }
    }

    fun getApiKeywords(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSearchKeywords(keyword).collect {
                _apiKeywords.value = it
            }
        }
    }


    fun filterDbKeywords(keyword: String) {
        _dbKeywords.value = _allDbKeywords.filter { model ->
            model.symbol.contains(keyword, ignoreCase = true)
        }
    }

    fun addKeywordToDb(item: SearchResponseItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addRecentSearch(item)
        }
    }
}