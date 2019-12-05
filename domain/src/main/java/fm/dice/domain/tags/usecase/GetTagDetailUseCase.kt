package fm.dice.domain.tags.usecase

import fm.dice.domain.tags.repository.TagRepository
import javax.inject.Inject

class GetTagDetailUseCase @Inject constructor(private val repository: TagRepository) {

    suspend operator fun invoke(tag: String) = repository.get(tag)
}