package paul.host.turkcell_test_task.data.network

import io.reactivex.Single
import paul.host.turkcell_test_task.data.model.Product
import paul.host.turkcell_test_task.data.model.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/developer-application-test/cart/list")
    fun products(): Single<Products>

    @GET("/developer-application-test/cart/{product_id}/detail")
    fun product(@Path("product_id") id: String): Single<Product>
}
