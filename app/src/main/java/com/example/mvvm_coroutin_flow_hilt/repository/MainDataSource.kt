package com.example.mvvm_coroutin_flow_hilt.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mvvm_coroutin_flow_hilt.model.ResponseBook
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainDataSource(
    private val query: String,
    private val ioDispatcher: CoroutineDispatcher,
    private val apiService: ApiService
) : PagingSource<Int, ResponseDocument>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseDocument> {
        return try {
            val page = params.key ?: 1

            val response = withContext(ioDispatcher) {
                apiService.getBookResponse(query = query)
            }



            val prevKey = if (page == 1) null else page - 1
            val nextKey =
                if (response.documents?.get(page) == null ) null else page + 1

            LoadResult.Page(
                data = response.documents,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, ResponseDocument>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }     }
}


