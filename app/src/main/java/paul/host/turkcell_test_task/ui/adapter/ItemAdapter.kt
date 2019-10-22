package paul.host.turkcell_test_task.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import paul.host.turkcell_test_task.R
import paul.host.turkcell_test_task.data.model.Product
import paul.host.turkcell_test_task.ui.NavigationListener
import java.util.*

class ItemAdapter(
    private val listener: NavigationListener,
    private var items: List<Product> = ArrayList()
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
                      .inflate(R.layout.fragment_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let { product ->
            holder.apply {
                setImage(product.image)
                name.text = product.name
                price.text = product.price.toString()
                itemView.setOnClickListener { listener.goDetails(product.id) }
            }
        }
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<Product>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.image)
        var name: TextView = view.findViewById(R.id.name)
        var price: TextView = view.findViewById(R.id.price)

        fun setImage(url: String) {
            Picasso.get()
                   .load(url)
                   .resize(50, 50)
                   .into(imageView)
        }
    }
}
