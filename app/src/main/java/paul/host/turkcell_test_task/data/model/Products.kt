package paul.host.turkcell_test_task.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("products")
    @Expose
    val products: List<Product>
)