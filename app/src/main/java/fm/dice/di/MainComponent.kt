package fm.dice.di

import fm.dice.data.common.di.scope.ActivityScope
import fm.dice.data.tags.di.TagDataModule
import fm.dice.tags.detail.di.TagDetailComponent
import fm.dice.tags.di.TagsComponent
import dagger.Component

@Component(
    modules = [
        MainSubComponentsModule::class,
        TagDataModule::class
    ]
)
@ActivityScope
interface MainComponent : TagsComponent.FactoryProvider, TagDetailComponent.FactoryProvider