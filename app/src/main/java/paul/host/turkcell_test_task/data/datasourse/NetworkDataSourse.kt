package paul.host.turkcell_test_task.data.datasourse

import io.reactivex.Single
import paul.host.turkcell_test_task.data.db.ProductDao
import paul.host.turkcell_test_task.data.model.Product
import paul.host.turkcell_test_task.data.network.ApiService
import timber.log.Timber
import javax.inject.Inject

class NetworkDataSourse @Inject constructor(private val api: ApiService) {
    fun products(): Single<List<Product>> = api.products()
        .map {
            it.products
        }.doOnError { Timber.e(it) }

    fun product(id: String) = api.product(id).doOnError { Timber.e(it) }
}