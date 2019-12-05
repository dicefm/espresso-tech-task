package fm.dice.data.tags.datastore

import fm.dice.data.tags.model.TagDetailResponse
import fm.dice.data.tags.model.TagsResponse

internal interface TagDataStore {

    interface Memory {

        fun get(): TagsResponse?

        fun get(tag: String): TagDetailResponse?

        fun insert(response: TagsResponse)

        fun insert(tag: String, response: TagDetailResponse)
    }

    interface Remote {

        suspend fun get(): TagsResponse

        suspend fun get(tag: String): TagDetailResponse
    }
}