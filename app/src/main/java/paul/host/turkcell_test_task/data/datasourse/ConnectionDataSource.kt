package paul.host.turkcell_test_task.data.datasourse

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.jakewharton.rx.ReplayingShare
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.reactivex.schedulers.Schedulers
import paul.host.turkcell_test_task.common.util.NetworkUtil
import timber.log.Timber
import javax.inject.Inject

@Suppress("DEPRECATION")
class ConnectionDataSource @Inject constructor(private val app: Application) {

    private var receiver: BroadcastReceiver? = null
    private var receiverIntent: Intent? = null
    private var status: Flowable<Boolean>? = null
    private var statusFlowableEmitter: FlowableEmitter<Boolean>? = null

    init {
        status =
            Flowable.create(FlowableOnSubscribe<Boolean> { this.registerReceiver(it) }, BackpressureStrategy.LATEST)
                    .defaultIfEmpty(false)
                    .doFinally { this.unregisterReceiver() }
                    .compose(ReplayingShare.instance())
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
    }

    fun hasConnection(): Flowable<Boolean> = status!!.doOnError { Timber.e(it) }

    private fun registerReceiver(emitter: FlowableEmitter<Boolean>) {
        if (receiverIntent == null) {
            statusFlowableEmitter = emitter
            receiver = ConnectionBroadcastReceiver(emitter)
            receiverIntent = app.registerReceiver(receiver, getIntentFilter())
        } else {
            unregisterReceiver()
            registerReceiver(emitter)
        }
    }

    private fun getIntentFilter(): IntentFilter = IntentFilter().apply {
        addAction(ConnectivityManager.CONNECTIVITY_ACTION)
    }

    private fun unregisterReceiver() {
        if (receiverIntent != null) {
            app.unregisterReceiver(receiver)
        }
        receiverIntent = null
        receiver = null
        statusFlowableEmitter = null
    }

    private inner class ConnectionBroadcastReceiver(
        private var emitter: FlowableEmitter<Boolean>
    ) : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            Thread { emitter.onNext(NetworkUtil.hasConnectionToNetwork(context)) }.start()
        }
    }
}