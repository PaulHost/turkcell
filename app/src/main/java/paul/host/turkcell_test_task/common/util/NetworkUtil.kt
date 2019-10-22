package paul.host.turkcell_test_task.common.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun hasConnectionToNetwork(context: Context) = getConnectivityManager(context).activeNetworkInfo
                                                                                  .isConnected

    private fun getConnectivityManager(context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}