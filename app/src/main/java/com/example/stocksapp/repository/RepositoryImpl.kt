package com.example.stocksapp.repository

import com.example.stocksapp.api.ApiService
import com.example.stocksapp.api.responsedto.graph.StockGraphDataItem
import com.example.stocksapp.api.responsedto.search.SearchResponseItem
import com.example.stocksapp.db.MapConverter
import com.example.stocksapp.db.StocksDatabase
import com.example.stocksapp.db.entity.ActivelyTradedEntity
import com.example.stocksapp.db.entity.CompanyOverviewEntity
import com.example.stocksapp.db.entity.SearchKeywordEntity
import com.example.stocksapp.db.entity.TopGainerEntity
import com.example.stocksapp.db.entity.TopLooserEntity
import com.example.stocksapp.presentation.details.CompanyOverviewScreenData
import com.example.stocksapp.presentation.explore.ExploreScreenData
import com.example.stocksapp.utils.EXPLORE_SCREEN_LAST_REFRESHED
import com.example.stocksapp.utils.ResponseModel
import com.example.stocksapp.utils.getSharedPref
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val db: StocksDatabase,
) : Repository {

    override fun getRecentKeywords(): Flow<List<SearchKeywordEntity>> =
        db.searchKeywordsDao.getAll()

    override fun getExploreScreenData(): Flow<ResponseModel<ExploreScreenData>> = flow {
        emit(ResponseModel.Loading)

        try {
            val dbTopGainers = db.topGainerDao.get6()
            val lastRefreshedTime = getSharedPref().getLong(EXPLORE_SCREEN_LAST_REFRESHED, 0L)
            val isOld = lastRefreshedTime == 0L ||
                    TimeUnit.MILLISECONDS.toDays(lastRefreshedTime) > TimeUnit.MILLISECONDS.toDays(
                System.currentTimeMillis()
            )

            if (dbTopGainers.isEmpty() || isOld) {
                val response = api.getTopGainersLosers()
                val topGainers = response.topGainers.map { it.toTopGainer() }
                val topLosers = response.topLosers.map { it.toTopLooser() }
                val activeTraded = response.mostActivelyTraded.map { it.toActivelyTraded() }
                db.topGainerDao.insertAll(topGainers)
                db.topLooserDao.insertAll(topLosers)
                db.activelyTradedDao.insertAll(activeTraded)

                getSharedPref().edit()
                    .putLong(EXPLORE_SCREEN_LAST_REFRESHED, System.currentTimeMillis()).apply()
                emit(
                    ResponseModel.Success(
                        ExploreScreenData(
                            topGainers.take(6),
                            topLosers.take(6),
                            activeTraded.take(6)
                        )
                    )
                )
            } else {
                val dbTopLosers = db.topLooserDao.get6()
                val dbActivelyTraded = db.activelyTradedDao.get6()

                emit(
                    ResponseModel.Success(
                        ExploreScreenData(
                            dbTopGainers,
                            dbTopLosers,
                            dbActivelyTraded
                        )
                    )
                )
            }
        } catch (e: Exception) {
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTopGainers(): Flow<ResponseModel<List<TopGainerEntity>>> = flow {
        emit(ResponseModel.Loading)

        try {
            var dbResponse = db.topGainerDao.getAll()
            if (dbResponse.isNotEmpty()) {
                emit(ResponseModel.Success(dbResponse))
            } else {
                val response = api.getTopGainersLosers()
                db.topGainerDao.insertAll(response.topGainers.map { it.toTopGainer() })
                db.topLooserDao.insertAll(response.topLosers.map { it.toTopLooser() })
                db.activelyTradedDao.insertAll(response.mostActivelyTraded.map { it.toActivelyTraded() })

                dbResponse = db.topGainerDao.getAll()
                emit(ResponseModel.Success(dbResponse))
            }
        } catch (e: Exception) {
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTopLooser(): Flow<ResponseModel<List<TopLooserEntity>>> = flow {
        emit(ResponseModel.Loading)

        try {
            var dbResponse = db.topLooserDao.getAll()
            if (dbResponse.isNotEmpty())
                emit(ResponseModel.Success(dbResponse))
            else {
                val response = api.getTopGainersLosers()
                db.topGainerDao.insertAll(response.topGainers.map { it.toTopGainer() })
                db.topLooserDao.insertAll(response.topLosers.map { it.toTopLooser() })
                db.activelyTradedDao.insertAll(response.mostActivelyTraded.map { it.toActivelyTraded() })

                dbResponse = db.topLooserDao.getAll()
                emit(ResponseModel.Success(dbResponse))
            }
        } catch (e: Exception) {
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getActivelyTraded(): Flow<ResponseModel<List<ActivelyTradedEntity>>> = flow {
        emit(ResponseModel.Loading)

        try {
            var dbResponse = db.activelyTradedDao.getAll()
            if (dbResponse.isNotEmpty())
                emit(ResponseModel.Success(dbResponse))
            else {
                val response = api.getTopGainersLosers()
                db.topGainerDao.insertAll(response.topGainers.map { it.toTopGainer() })
                db.topLooserDao.insertAll(response.topLosers.map { it.toTopLooser() })
                db.activelyTradedDao.insertAll(response.mostActivelyTraded.map { it.toActivelyTraded() })

                dbResponse = db.activelyTradedDao.getAll()
                emit(ResponseModel.Success(dbResponse))
            }
        } catch (e: Exception) {
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getSearchKeywords(keyword: String): Flow<ResponseModel<List<SearchResponseItem>>> =
        flow {
            emit(ResponseModel.Loading)

            try {
                val apiResponse = api.getSearchKeywords(keyword = keyword)

                emit(ResponseModel.Success(apiResponse.bestMatches))
            } catch (e: Exception) {
                emit(ResponseModel.Error(e.message ?: "Something went wrong"))
            }
        }

    override fun getCompanyOverview(symbol: String): Flow<ResponseModel<CompanyOverviewScreenData>> =
        flow {
            emit(ResponseModel.Loading)

            try {
                val dbResponse = db.companyOverviewDao.getBySymbol(symbol)
                if (dbResponse != null) {
                    val screenData = dbResponse.toScreenData()
                    emit(ResponseModel.Success(screenData))
                } else {

                    val apiResponse = api.getCompanyOverview(symbol = symbol)
                    val graphResponse = api.getDailyTimeSeries(symbol = symbol)
                    val mapJson = MapConverter.fromMap(graphResponse.timeSeriesDaily);
                    val entity: CompanyOverviewEntity = apiResponse.toEntity(mapJson)

                    db.companyOverviewDao.insert(entity)
                    emit(ResponseModel.Success(entity.toScreenData()))
                }
            } catch (e: Exception) {
                emit(ResponseModel.Error(e.message ?: "Something went wrong"))
            }
        }

    override fun reloadCompanyOverview(symbol: String): Flow<ResponseModel<CompanyOverviewScreenData>> =
        flow {
            emit(ResponseModel.Loading)

            try {
                val apiResponse = api.getCompanyOverview(symbol = symbol)
                val graphResponse = api.getDailyTimeSeries(symbol = symbol)
                val mapJson = MapConverter.fromMap(graphResponse.timeSeriesDaily);
                val entity: CompanyOverviewEntity = apiResponse.toEntity(mapJson)

                db.companyOverviewDao.insert(entity)

                emit(ResponseModel.Success(entity.toScreenData()))
            } catch (e: Exception) {
                emit(ResponseModel.Error(e.message ?: "Something went wrong"))
            }
        }

    override fun getCompanyDailyGraph(symbol: String): Flow<ResponseModel<Map<String, StockGraphDataItem>>> =
        flow {
            emit(ResponseModel.Loading)

            try {
                val apiResponse = api.getDailyTimeSeries(symbol = symbol)
                emit(ResponseModel.Success(apiResponse.timeSeriesDaily))
            } catch (e: Exception) {
                emit(ResponseModel.Error(e.message ?: "Something went wrong"))
            }
        }

    override fun getCompanyWeeklyGraph(symbol: String): Flow<ResponseModel<Map<String, StockGraphDataItem>>> =
        flow {
            emit(ResponseModel.Loading)

            try {
                val apiResponse = api.getWeeklyTimeSeries(symbol = symbol)

                emit(ResponseModel.Success(apiResponse.timeSeriesWeekly))
            } catch (e: Exception) {
                emit(ResponseModel.Error(e.message ?: "Something went wrong"))
            }
        }

    override fun getCompanyMonthlyGraph(symbol: String): Flow<ResponseModel<Map<String, StockGraphDataItem>>> =
        flow {
            emit(ResponseModel.Loading)

            try {
                val apiResponse = api.getMonthlyTimeSeries(symbol = symbol)

                emit(ResponseModel.Success(apiResponse.timeSeriesMonthly))
            } catch (e: Exception) {
                emit(ResponseModel.Error(e.message ?: "Something went wrong"))
            }
        }

    override suspend fun addRecentSearch(keyword: SearchResponseItem) =
        db.searchKeywordsDao.insert(keyword.toEntity())
}