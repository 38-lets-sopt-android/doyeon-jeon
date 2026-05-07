package com.example.letssopt.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.letssopt.data.mapper.toModel
import com.example.letssopt.data.remote.service.UserService
import com.example.letssopt.domain.model.UserModel

class UserPagingSource(
    private val userService: UserService,
) : PagingSource<Int, UserModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> =
        runCatching {
            userService.getUserList()
        }.fold(
            onSuccess = { response ->
                LoadResult.Page(
                    data = response.data?.toModel() ?: emptyList(),
                    prevKey = null,
                    nextKey = null,
                )
            },
            onFailure = { LoadResult.Error(it) }
        )

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? = null
}
