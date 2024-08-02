package service
import model.Product
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}