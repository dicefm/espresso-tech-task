package fm.dice.tags.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fm.dice.common.base.BaseViewModel
import fm.dice.domain.tags.entity.TagDetailEntity
import fm.dice.domain.tags.usecase.GetTagDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TagDetailViewModel @Inject constructor(
    private val getTagDetail: GetTagDetailUseCase
) : BaseViewModel() {

    private val detail = MutableLiveData<List<TagDetailEntity>>()

    fun init(tag: String) {
        viewModelScope.launch(exceptionHandler) {
            detail.value = getTagDetail(tag)
        }.addToLoadingState()
    }

    fun detail(): LiveData<List<TagDetailEntity>> = detail
}