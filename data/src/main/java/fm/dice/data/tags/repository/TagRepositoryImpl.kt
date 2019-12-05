package fm.dice.data.tags.repository

import fm.dice.data.tags.datastore.TagDataStore
import fm.dice.data.tags.mapper.TagDetailEntityMapper
import fm.dice.domain.tags.entity.TagDetailEntity
import fm.dice.domain.tags.repository.TagRepository
import javax.inject.Inject

internal class TagRepositoryImpl @Inject constructor(
    private val memory: TagDataStore.Memory,
    private val remote: TagDataStore.Remote,
    private val mapper: TagDetailEntityMapper
) : TagRepository {

    override suspend fun get(): List<String> {
        val source = memory.get() ?: remote.get().also(memory::insert)
        return source._embedded.orEmpty()
    }

    override suspend fun get(tag: String): List<TagDetailEntity> {
        val source = memory.get(tag) ?: remote.get(tag).also { memory.insert(tag, it) }
        return source._embedded?.tags.orEmpty()
            .map { mapper.mapFrom(TagDetailEntityMapper.Params(tag, it)) }
    }
}