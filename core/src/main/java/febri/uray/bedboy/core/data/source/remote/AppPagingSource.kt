package febri.uray.bedboy.core.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import febri.uray.bedboy.core.data.source.remote.network.ApiService
import febri.uray.bedboy.core.domain.model.Album
import febri.uray.bedboy.core.util.DataMapper

class AppPagingSource(private val apiService: ApiService) : PagingSource<Int, Album>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData =
                apiService.getAllAlbum(page = params.key ?: 1, limit = params.loadSize)

            LoadResult.Page(
                data = DataMapper.mapResponseToAlbumDomain(responseData),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}