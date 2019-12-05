package fm.dice.domain.tags.usecase

import fm.dice.domain.tags.repository.TagRepository
import javax.inject.Inject

class GetTagsUseCase @Inject constructor(private val repository: TagRepository) {

    suspend operator fun invoke() = repository.get().shuffled()
}