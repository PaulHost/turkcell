package paul.host.navico_testtask.di.module

import dagger.Module
import dagger.Provides
import paul.host.turkcell_test_task.data.network.ApiService
import paul.host.navico_testtask.data.repasitory.Repository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(api: ApiService) = Repository(api)
}