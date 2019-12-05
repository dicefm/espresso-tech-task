package fm.dice.di

import fm.dice.tags.detail.di.TagDetailComponent
import fm.dice.tags.di.TagsComponent
import dagger.Module

@Module(
    subcomponents = [
        TagsComponent::class,
        TagDetailComponent::class
    ]
)
abstract class MainSubComponentsModule