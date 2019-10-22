package paul.host.turkcell_test_task.data.datasourse

import io.reactivex.Flowable
import paul.host.turkcell_test_task.data.db.ProductDao
import paul.host.turkcell_test_task.data.model.Product
import timber.log.Timber
import javax.inject.Inject

class DataBaseDataSource @Inject constructor(private val dao: ProductDao) {
    fun products(): Flowable<List<Product>> = dao.products().doOnError { Timber.e(it) }

    fun product(id: String): Flowable<Product> = dao.product(id).doOnError { Timber.e(it) }

    fun saveProducts(products: List<Product>) {
        if (products.isNotEmpty()) dao.insert(products)
    }

    fun saveProduct(product: Product) = dao.insert(product)
}