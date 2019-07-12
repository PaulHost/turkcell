package paul.host.turkcell_test_task.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import paul.host.turkcell_test_task.App
import paul.host.turkcell_test_task.R
import paul.host.turkcell_test_task.data.repasitory.Repository
import paul.host.turkcell_test_task.ui.base.BaseFragment
import javax.inject.Inject

class DetailsFragment : BaseFragment() {
    @Inject
    lateinit var repository: Repository
    lateinit var image: ImageView
    lateinit var name: TextView
    lateinit var price: TextView
    lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_details, container, false)
                .apply {
                    image = findViewById(R.id.imageView)
                    name = findViewById(R.id.name)
                    price = findViewById(R.id.price)
                    description = findViewById(R.id.description)
                }

    override fun onStart() {
        super.onStart()
        arguments?.let { a ->
            repository.product(a[ID] as String)
                      .subscribeOn(Schedulers.io())
                      .unsubscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe { product ->
                          run {
                              product.let { p ->
                                  Picasso.get()
                                      .load(p.image)
                                      .into(image)
                                  name.text = p.name
                                  price.text = p.price.toString()
                                  p.description.let { description.text = it }
                              }
                          }
                      }

        }
    }

    companion object {
        const val ID = "product_id"

        fun newInstance(id: String): DetailsFragment {
            val instance = DetailsFragment()
            instance.arguments = Bundle().apply {
                putString(ID, id)
            }
            return instance
        }
    }
}