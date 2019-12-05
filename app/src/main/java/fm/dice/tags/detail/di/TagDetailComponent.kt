package fm.dice.tags.detail.di

import fm.dice.tags.detail.TagDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [TagDetailPresentationModule::class])
interface TagDetailComponent {

    fun inject(fragment: TagDetailFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(): TagDetailComponent
    }

    interface FactoryProvider {

        fun provideTagDetailComponentFactory(): Factory
    }
}