package fm.dice.tags.di

import androidx.lifecycle.ViewModel
import fm.dice.common.di.viewmodel.ViewModelKey
import fm.dice.tags.TagsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TagsPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(TagsViewModel::class)
    abstract fun bindViewModel(viewModel: TagsViewModel): ViewModel
}