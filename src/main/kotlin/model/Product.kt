package model

data class Product(
    val id: String,
    val SKU: String,
    val name: String,
    var price: Double,
    val type: ProductType,
    val image: String,
    var stock: Int = 0
)

enum class ProductType {
    NORMAL,
    HIGH_DEMAND
}
