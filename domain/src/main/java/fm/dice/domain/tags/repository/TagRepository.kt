package fm.dice.domain.tags.repository

import fm.dice.domain.tags.entity.TagDetailEntity

interface TagRepository {

    suspend fun get(): List<String>

    suspend fun get(tag: String): List<TagDetailEntity>
}