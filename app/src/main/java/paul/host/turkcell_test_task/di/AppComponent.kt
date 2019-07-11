package paul.host.navico_testtask.di

import dagger.Component
import paul.host.navico_testtask.di.module.AppModule
import paul.host.turkcell_test_task.App
import paul.host.turkcell_test_task.ui.fragment.ListFragment
import javax.inject.Singleton

@Suppress("DEPRECATION")
@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: ListFragment)

    object Initializer {
        fun init(app: App): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
        }
    }
}