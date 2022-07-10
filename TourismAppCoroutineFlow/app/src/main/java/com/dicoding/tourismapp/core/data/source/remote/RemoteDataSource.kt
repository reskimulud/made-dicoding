package com.dicoding.tourismapp.core.data.source.remote

import android.util.Log
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.network.ApiService
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {
    suspend fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> = flow {
        try {
            val response = apiService.getList()
            val dataArray = response.places
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(dataArray))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (err: Exception) {
            emit(ApiResponse.Error(err.toString()))
            Log.e("RemoteDataSource", err.toString())
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }
}

