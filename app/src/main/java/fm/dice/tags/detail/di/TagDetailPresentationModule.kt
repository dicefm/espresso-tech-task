package fm.dice.tags.detail.di

import androidx.lifecycle.ViewModel
import fm.dice.common.di.viewmodel.ViewModelKey
import fm.dice.tags.TagsViewModel
import fm.dice.tags.detail.TagDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TagDetailPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(TagDetailViewModel::class)
    abstract fun bindViewModel(viewModel: TagDetailViewModel): ViewModel
}