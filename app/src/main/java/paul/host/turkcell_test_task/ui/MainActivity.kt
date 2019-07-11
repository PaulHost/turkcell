package paul.host.turkcell_test_task.ui

import android.os.Bundle
import paul.host.turkcell_test_task.ui.base.BaseActivity
import paul.host.turkcell_test_task.R

class MainActivity : BaseActivity() {

    override val container: Int
        get() = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        TODO navigation:
//        if (isBackStackEmpty) startFragment(ListFragment.getInstance())
    }
}
