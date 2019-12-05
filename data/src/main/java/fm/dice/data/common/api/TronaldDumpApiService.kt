package fm.dice.data.common.api

import fm.dice.data.tags.model.TagDetailResponse
import fm.dice.data.tags.model.TagsResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface TronaldDumpApiService {

    @GET("tag")
    suspend fun get(): TagsResponse

    @GET("tag/{tag}")
    suspend fun get(@Path("tag") tag: String): TagDetailResponse
}