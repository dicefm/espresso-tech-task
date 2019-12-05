package fm.dice.domain.tags.usecase

import fm.dice.domain.tags.repository.TagRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Assert.*
import org.junit.Test

class GetTagDetailUseCaseTest {

    private val repository: TagRepository = mock()
    private val useCase = GetTagDetailUseCase(repository)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testShouldCallGetWithCorrectTag() {
        runBlocking {
            val tag = "Bernie Sanders"

            useCase.invoke(tag)

            verify(repository).get(tag)
        }
    }
}