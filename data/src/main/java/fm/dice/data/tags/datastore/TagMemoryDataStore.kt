package fm.dice.data.tags.datastore

import fm.dice.data.common.CacheKey
import fm.dice.data.common.getIfFresh
import fm.dice.data.common.update
import fm.dice.data.tags.model.TagDetailResponse
import fm.dice.data.tags.model.TagsResponse
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class TagMemoryDataStore @Inject constructor() : TagDataStore.Memory {

    private var tags: TagsResponse? = null

    private val detailTimeToLive = TimeUnit.SECONDS.toMillis(10)
    private val detail: LinkedHashMap<CacheKey<String>, TagDetailResponse> = linkedMapOf()

    override fun get(): TagsResponse? {
        return tags
    }

    override fun get(tag: String): TagDetailResponse? {
        return detail.getIfFresh(tag, detailTimeToLive)
    }

    override fun insert(response: TagsResponse) {
        tags = response
    }

    override fun insert(tag: String, response: TagDetailResponse) {
        detail.update(tag, response)
    }
}