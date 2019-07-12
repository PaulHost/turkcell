package paul.host.turkcell_test_task.di

import dagger.Component
import paul.host.turkcell_test_task.App
import paul.host.navico_testtask.di.module.AppModule
import paul.host.navico_testtask.di.module.NetworkModule
import paul.host.turkcell_test_task.di.module.DataModule
import paul.host.turkcell_test_task.ui.fragment.DetailsFragment
import paul.host.turkcell_test_task.ui.fragment.ListFragment
import javax.inject.Singleton

@Suppress("DEPRECATION")
@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: ListFragment)
    fun inject(fragment: DetailsFragment)

    object Initializer {
        fun init(app: App): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .networkModule(NetworkModule())
                .dataModule(DataModule())
                .build()
        }
    }
}