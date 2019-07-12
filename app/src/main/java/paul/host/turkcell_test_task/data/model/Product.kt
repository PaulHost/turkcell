package paul.host.turkcell_test_task.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Product(
    @SerializedName("product_id")
    @Expose
    @PrimaryKey
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("price")
    @Expose
    val price: Int,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("description")
    @Expose
    val description: String? = null
)