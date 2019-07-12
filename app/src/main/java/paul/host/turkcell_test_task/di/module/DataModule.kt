package paul.host.turkcell_test_task.di.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import paul.host.turkcell_test_task.data.datasourse.ConnectionDataSource
import paul.host.turkcell_test_task.data.datasourse.DataBaseDataSource
import paul.host.turkcell_test_task.data.datasourse.NetworkDataSourse
import paul.host.turkcell_test_task.data.db.DataBase
import paul.host.turkcell_test_task.data.db.ProductDao
import paul.host.turkcell_test_task.data.network.ApiService
import paul.host.turkcell_test_task.data.repasitory.Repository
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDataBase(app: Application) = Room.databaseBuilder(app, DataBase::class.java, "database").build()

    @Singleton
    @Provides
    fun provideDao(db: DataBase) = db.dao()

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        api: ApiService,
        dao: ProductDao
    ) = NetworkDataSourse(api, dao)

    @Singleton
    @Provides
    fun provideDataBaseDataSource(dao: ProductDao) = DataBaseDataSource(dao)

    @Singleton
    @Provides
    fun provideConnectionDataSource(app: Application) = ConnectionDataSource(app)

    @Singleton
    @Provides
    fun provideRepository(
        newtworkDataSource: NetworkDataSourse,
        dataBaseDataSource: DataBaseDataSource,
        connectionDataSource: ConnectionDataSource
    ) = Repository(
        api = newtworkDataSource,
        db = dataBaseDataSource,
        connection = connectionDataSource
    )
}