package service
import model.Inventory
import model.Product
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("inventories")
    suspend fun getInventories(): List<Inventory>
}