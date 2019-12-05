package fm.dice.domain.tags.usecase

import fm.dice.domain.tags.repository.TagRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

class GetTagsUseCaseTest {

    private val repository: TagRepository = mock()
    private val useCase = GetTagsUseCase(repository)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testTagsSortedInAlphabeticalOrder() {
        runBlocking {
            val tags = listOf("D", "C", "B", "A")
            whenever(repository.get()).thenReturn(tags)

            val output = useCase.invoke()

            assert(output[0] == "A")
            assert(output[1] == "B")
            assert(output[2] == "C")
            assert(output[3] == "D")

            verify(repository).get()
        }
    }
}