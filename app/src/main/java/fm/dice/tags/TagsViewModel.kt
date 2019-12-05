package fm.dice.tags

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fm.dice.common.base.BaseViewModel
import fm.dice.domain.tags.usecase.GetTagsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TagsViewModel @Inject constructor(
    private val getTags: GetTagsUseCase
) : BaseViewModel() {

    private val tags = MutableLiveData<List<String>>()

    init {
        viewModelScope.launch(exceptionHandler) {
            tags.value = getTags()
        }.addToLoadingState()
    }

    fun tags(): LiveData<List<String>> = tags
}