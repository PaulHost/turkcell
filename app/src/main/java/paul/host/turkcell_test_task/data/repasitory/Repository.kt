package paul.host.navico_testtask.data.repasitory

import io.reactivex.Single
import paul.host.turkcell_test_task.data.model.Product
import paul.host.turkcell_test_task.data.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiService) {
    fun products(): Single<List<Product>> = api.products().map { it.products }

    fun produt(id: String) = api.product(id)
}
