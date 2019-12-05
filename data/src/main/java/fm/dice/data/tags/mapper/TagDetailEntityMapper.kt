package fm.dice.data.tags.mapper

import fm.dice.data.common.mapper.Mapper
import fm.dice.data.tags.model.TagDetailResponse
import fm.dice.domain.tags.entity.TagDetailEntity
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import javax.inject.Inject

internal class TagDetailEntityMapper @Inject constructor() : Mapper<TagDetailEntityMapper.Params, TagDetailEntity>() {

    override fun mapFrom(from: Params): TagDetailEntity {
        return TagDetailEntity(
            updatedAt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").parseDateTime(from.response.appeared_at),
            author = from.response._embedded?.author?.firstOrNull()?.name.orEmpty(),
            authorQuote = from.response.value.orEmpty(),
            tags = from.response.tags?.filterNot { it.equals(from.searchedTag, true) }.orEmpty(),
            sourceUrl = from.response._embedded?.source?.firstOrNull()?.url.orEmpty()
        )
    }

    data class Params(val searchedTag: String, val response: TagDetailResponse.Embedded.Tag)
}