package paul.host.turkcell_test_task.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import paul.host.turkcell_test_task.ui.NavigationListener
import timber.log.Timber

abstract class BaseFragment : Fragment() {
    var listener: NavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener)
            this.listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun onError(throwable: Throwable) = Timber.e(throwable)

}
