package paul.host.turkcell_test_task.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Product (
    @SerializedName("product_id")
    @PrimaryKey
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String? = null
)