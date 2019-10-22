package paul.host.turkcell_test_task.data.repasitory

import io.reactivex.Flowable
import paul.host.turkcell_test_task.data.datasourse.ConnectionDataSource
import paul.host.turkcell_test_task.data.datasourse.DataBaseDataSource
import paul.host.turkcell_test_task.data.datasourse.NetworkDataSourse
import paul.host.turkcell_test_task.data.model.Product
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: NetworkDataSourse,
    private val db: DataBaseDataSource,
    private val connection: ConnectionDataSource
) {

    fun products(): Flowable<List<Product>> = db.products().switchIfEmpty{
        apiProducts().subscribe()
    }

    fun product(id: String): Flowable<Product> = db.product(id).switchIfEmpty{
        apiProduct(id).subscribe()
    }

    private fun hasConnection() = connection.hasConnection()
                                            .distinctUntilChanged()

    private fun apiProduct(id: String): Flowable<Product> = hasConnection().flatMap {
        if (it) api.product(id)
                   .toFlowable()
                   .doOnNext(db::saveProduct)
        else Flowable.empty()
    }

    private fun apiProducts(): Flowable<List<Product>> = hasConnection().flatMap {
        if (it) {
            api.products()
               .toFlowable()
               .doOnNext(db::saveProducts)
        } else {
            Flowable.empty()
        }
    }

}