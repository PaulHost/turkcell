package paul.host.turkcell_test_task.ui.fragment

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
import paul.host.turkcell_test_task.data.model.Product
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
        super.onCreate(savedInstanceState)
        App.component.inject(this)
    }

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
        arguments?.let {
            repository.product(it[ID] as String)
                      .subscribeOn(Schedulers.io())
                      .unsubscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(
                          this::setContent,
                          this::onError
                      )
        }
    }

    private fun setContent(content: Product) = content.let { product ->
        Picasso.get()
               .load(product.image)
               .into(image)
        name.text = product.name
        price.text = product.price.toString()
        description.text = product.description
    }

    companion object {
        const val ID = "product_id"

        fun getInstance(id: String) = DetailsFragment().apply {
                arguments?.putString(ID, id)
            }
    }

}