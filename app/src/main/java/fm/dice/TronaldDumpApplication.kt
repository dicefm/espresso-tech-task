package fm.dice

import android.app.Application
import timber.log.Timber

class TronaldDumpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}