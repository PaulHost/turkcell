package paul.host.turkcell_test_task

import android.app.Application
import paul.host.navico_testtask.di.AppComponent
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var instans: App
            private set
        lateinit var component: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instans = this
        component = AppComponent.Initializer.init(app = this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}