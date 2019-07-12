package paul.host.turkcell_test_task.data.db

import android.arch.persistence.room.*
import io.reactivex.Flowable
import paul.host.turkcell_test_task.data.model.Product


@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun dao(): ProductDao
}

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun products(): Flowable<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun product(id: String): Flowable<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(products: List<Product>)

    @Update
    fun update(product: Product)

    @Update
    fun update(products: List<Product>)

    @Delete
    fun delete(product: Product)
}
