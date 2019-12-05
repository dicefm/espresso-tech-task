package fm.dice.data.tags.di

import fm.dice.data.common.di.TronaldDumpApiModule
import fm.dice.data.common.di.scope.ActivityScope
import fm.dice.data.tags.datastore.TagDataStore
import fm.dice.data.tags.datastore.TagMemoryDataStore
import fm.dice.data.tags.datastore.TagRemoteDataStore
import fm.dice.data.tags.repository.TagRepositoryImpl
import fm.dice.domain.tags.repository.TagRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module(includes = [TronaldDumpApiModule::class])
abstract class TagDataModule {

    @Binds
    @Reusable
    internal abstract fun bindRepository(impl: TagRepositoryImpl): TagRepository

    @Binds
    @Reusable
    internal abstract fun bindRemote(impl: TagRemoteDataStore): TagDataStore.Remote

    @Binds
    @ActivityScope
    internal abstract fun bindMemory(impl: TagMemoryDataStore): TagDataStore.Memory
}