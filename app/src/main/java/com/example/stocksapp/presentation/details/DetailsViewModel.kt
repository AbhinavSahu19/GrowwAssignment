package com.example.stocksapp.presentation.details

import android.annotation.SuppressLint
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocksapp.api.responsedto.graph.StockGraphDataItem
import com.example.stocksapp.repository.Repository
import com.example.stocksapp.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.yashctn88.candlechartcompose.model.CandlestickData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val repository: Repository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    val symbol: String =
        checkNotNull(savedStateHandle["symbol"])

    private val _companyOverview =
        MutableStateFlow<ResponseModel<CompanyOverviewScreenData>>(ResponseModel.Loading)
    val companyOverview: StateFlow<ResponseModel<CompanyOverviewScreenData>> = _companyOverview

    private val _graphDurationEnum = MutableStateFlow<GraphDurationEnum>(GraphDurationEnum.DAYS_7)
    val graphDurationEnum: StateFlow<GraphDurationEnum> = _graphDurationEnum

    private var allGraphList: List<Pair<CandlestickData, LocalDate>> = listOf()

    private val _graphList = MutableStateFlow<List<CandlestickData>>(emptyList())
    val graphList: StateFlow<List<CandlestickData>> = _graphList

    init {
        getCompanyOverview()
    }

    fun getCompanyOverview() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCompanyOverview(symbol).collectLatest {
                _companyOverview.value = it
                if (_companyOverview.value is ResponseModel.Success<CompanyOverviewScreenData>) {
                    makeGraphList(
                        (it as ResponseModel.Success<CompanyOverviewScreenData>).data.dailyGraphData
                    )
                }
            }
        }
    }

    @SuppressLint("NewApi")
    fun makeGraphList(dailyGraphData: Map<String, StockGraphDataItem>) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        allGraphList = dailyGraphData
            .mapNotNull { (dateString, item) ->
                try {
                    val date = LocalDate.parse(dateString, formatter)
                    CandlestickData(
                        x = dateString,
                        open = item.open.toFloat(),
                        high = item.high.toFloat(),
                        low = item.low.toFloat(),
                        close = item.close.toFloat(),
                    ) to date
                } catch (e: Exception) {
                    null
                }
            }

        filterGraph(GraphDurationEnum.MONTHS_1)
    }

    @SuppressLint("NewApi")
    fun filterGraph(duration: GraphDurationEnum) {
        _graphDurationEnum.value = duration

        val today = LocalDate.now()

        val filtered = allGraphList
            .filter { (_, date) ->
                when (duration) {
                    GraphDurationEnum.DAYS_7 -> date.isAfter(today.minusDays(7)) || date.isEqual(
                        today.minusDays(7)
                    )

                    GraphDurationEnum.DAYS_15 -> date.isAfter(today.minusDays(15)) || date.isEqual(
                        today.minusDays(15)
                    )

                    GraphDurationEnum.MONTHS_1 -> date.isAfter(today.minusDays(30)) || date.isEqual(
                        today.minusDays(30)
                    )

                    GraphDurationEnum.MONTHS_3 -> date.isAfter(today.minusMonths(3)) || date.isEqual(
                        today.minusMonths(3)
                    )

                    GraphDurationEnum.MONTHS_6 -> date.isAfter(today.minusMonths(6)) || date.isEqual(
                        today.minusMonths(6)
                    )

                    GraphDurationEnum.YEARS_1 -> date.isAfter(today.minusYears(1)) || date.isEqual(
                        today.minusYears(1)
                    )

                    GraphDurationEnum.ALL_TIME -> true
                }
            }
            .sortedBy { (_, date) -> date }
            .map { (item, _) -> item }

        _graphList.value = filtered
    }
}
