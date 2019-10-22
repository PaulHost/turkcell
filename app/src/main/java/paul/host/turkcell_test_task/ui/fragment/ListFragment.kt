package paul.host.turkcell_test_task.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import paul.host.turkcell_test_task.App
import paul.host.turkcell_test_task.R
import paul.host.turkcell_test_task.data.model.Product
import paul.host.turkcell_test_task.data.repasitory.Repository
import paul.host.turkcell_test_task.ui.adapter.ItemAdapter
import paul.host.turkcell_test_task.ui.base.BaseFragment
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class ListFragment : BaseFragment() {

    private lateinit var adapter: ItemAdapter

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
        adapter = ItemAdapter(listener = listener!!)
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_list, container, false).apply {
            if (this is RecyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = this@ListFragment.adapter
                repository.products()
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        this@ListFragment::setContent,
                        this@ListFragment::onError
                    )
            }
        }

    private fun setContent(products: List<Product>) = adapter.setItems(products)

    companion object {
        fun getInstance(): ListFragment = ListFragment()
    }
}
