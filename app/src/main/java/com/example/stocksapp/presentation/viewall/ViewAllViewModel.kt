package com.example.stocksapp.presentation.viewall

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocksapp.db.entity.ActivelyTradedEntity
import com.example.stocksapp.db.entity.SearchKeywordEntity
import com.example.stocksapp.db.entity.TopGainerEntity
import com.example.stocksapp.db.entity.TopLooserEntity
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
class ViewAllViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val type = savedStateHandle.get<String>("type")?.let {
        ViewAllType.valueOf(it)
    } ?: ViewAllType.ACTIVELY_TRADED


    private val _topGainers =
        MutableStateFlow<ResponseModel<List<TopGainerEntity>>?>(null)
    val topGainers: StateFlow<ResponseModel<List<TopGainerEntity>>?> = _topGainers

    private val _topLosers =
        MutableStateFlow<ResponseModel<List<TopLooserEntity>>?>(null)
    val topLosers: StateFlow<ResponseModel<List<TopLooserEntity>>?> = _topLosers

    private val _activelyTraded =
        MutableStateFlow<ResponseModel<List<ActivelyTradedEntity>>?>(null)
    val activelyTraded: StateFlow<ResponseModel<List<ActivelyTradedEntity>>?> = _activelyTraded

    private val _recentSearches =
        MutableStateFlow<List<SearchKeywordEntity>?>(null)
    val recentSearches: StateFlow<List<SearchKeywordEntity>?> = _recentSearches

    init {
        Log.d("StockApp", type.toString())
        getData()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (type) {
                ViewAllType.TOP_GAINER -> {
                    repository.getTopGainers().collect {
                        _topGainers.value = it
                    }
                }

                ViewAllType.TOP_LOSER -> {
                    repository.getTopLooser().collect {
                        _topLosers.value = it
                    }
                }

                ViewAllType.ACTIVELY_TRADED -> {
                    repository.getActivelyTraded().collect {
                        _activelyTraded.value = it
                    }
                }

                ViewAllType.RECENT_SEARCHES -> {
                    repository.getRecentKeywords().collectLatest {
                        _recentSearches.value = it
                    }
                }
            }
        }
    }
}