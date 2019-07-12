package paul.host.turkcell_test_task.common.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

@Suppress("UNNECESSARY_SAFE_CALL", "MemberVisibilityCanBePrivate")
class NetworkUtil {

    companion object {

        fun getConnectivityManager(context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        fun getNetInfo(context: Context): NetworkInfo? =
            getConnectivityManager(context)?.activeNetworkInfo

        fun hasConnectionToNetwork(context: Context) =
            getNetInfo(context)?.isConnected ?: false
    }
}