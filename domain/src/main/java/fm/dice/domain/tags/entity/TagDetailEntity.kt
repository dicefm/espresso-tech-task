package fm.dice.domain.tags.entity

import org.joda.time.DateTime

data class TagDetailEntity(
    val updatedAt: DateTime,
    val author: String,
    val authorQuote: String,
    val tags: List<String>,
    val sourceUrl: String
)