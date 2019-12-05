package fm.dice.data.tags.mapper

import fm.dice.data.tags.model.TagDetailResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*

class TagDetailEntityMapperTest {

    private val mapper = TagDetailEntityMapper()

    @Test
    fun testTagsShouldNotContainSearchTerm() {
        val searchTerm = "Apologies"
        val tags = listOf(searchTerm, "Bernie Sanders")
        val response: TagDetailResponse.Embedded.Tag = mock()
        val params = TagDetailEntityMapper.Params(searchTerm, response)

        whenever(response.appeared_at).thenReturn("2016-10-10T00:00:00")
        whenever(response.tags).thenReturn(tags)

        val output = mapper.mapFrom(params)

        assertFalse(output.tags.contains(searchTerm))
    }
}