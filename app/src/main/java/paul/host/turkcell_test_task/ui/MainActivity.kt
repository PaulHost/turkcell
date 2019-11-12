package paul.host.turkcell_test_task.ui

import android.os.Bundle
import paul.host.turkcell_test_task.R
import paul.host.turkcell_test_task.ui.base.BaseActivity
import paul.host.turkcell_test_task.ui.fragment.DetailsFragment
import paul.host.turkcell_test_task.ui.fragment.ListFragment

class MainActivity : BaseActivity() {

    override val container: Int = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState ?: run {
            startFragment(ListFragment.getInstance())
        }
    }

    override fun goDetails(id: String) {
        startFragment(DetailsFragment.getInstance(id))
    }

}
