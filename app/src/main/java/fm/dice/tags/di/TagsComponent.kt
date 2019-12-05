package fm.dice.tags.di

import fm.dice.tags.TagsFragment
import dagger.Subcomponent

@Subcomponent(modules = [TagsPresentationModule::class])
interface TagsComponent {

    fun inject(fragment: TagsFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(): TagsComponent
    }

    interface FactoryProvider {

        fun provideTagsComponentFactory(): Factory
    }
}