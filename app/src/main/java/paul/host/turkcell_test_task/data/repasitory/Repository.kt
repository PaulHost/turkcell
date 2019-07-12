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
    fun products(): Flowable<List<Product>> = hasConnection().flatMapSingle {
        if (it) api.products() else db.products().firstOrError()
    }

    fun product(id: String): Flowable<Product> = hasConnection().flatMapSingle {
        if (it) api.product(id) else db.product(id).firstOrError()
    }

    private fun hasConnection() = connection.hasConnection()

}