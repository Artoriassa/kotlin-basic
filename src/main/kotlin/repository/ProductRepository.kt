package repository
import model.Inventory
import model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.ProductService

class ProductRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productService = retrofit.create(ProductService::class.java)

    suspend fun fetchProducts(): List<Product> {
        return productService.getProducts()
    }

    suspend fun fetchInventories(): List<Inventory> {
        return productService.getInventories()
    }
}