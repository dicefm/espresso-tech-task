package fm.dice.data.tags.datastore

import fm.dice.data.tags.model.TagsResponse
import fm.dice.data.common.api.TronaldDumpApiService
import fm.dice.data.tags.model.TagDetailResponse
import javax.inject.Inject

internal class TagRemoteDataStore @Inject constructor(
    private val tronaldDumpApiService: TronaldDumpApiService
) : TagDataStore.Remote {

    override suspend fun get(): TagsResponse {
        return tronaldDumpApiService.get()
    }

    override suspend fun get(tag: String): TagDetailResponse {
        return tronaldDumpApiService.get(tag)
    }
}