package paul.host.navico_testtask.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import paul.host.turkcell_test_task.App
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun provideApplication(): Application = app
}