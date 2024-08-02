import com.thoughtworks.kotlin_basic.util.PrintUtil
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import model.Inventory
import model.Product
import model.ProductType
import repository.ProductRepository

fun main(args: Array<String>) {
    println("Hello World!")
    println("Program arguments: ${args.joinToString()}")

    val printUtil = PrintUtil()
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.

    val headers = listOf("ID", "Name", "Occupation")
    val rows = listOf(
        listOf("1", "Alice", "Software Engineer"),
        listOf("2", "Bob", "Data Scientist"),
        listOf("3", "Charlie", "Product Manager")
    )

    printUtil.printTable(headers, rows)

    println("|--------------------- assignment 1 -----------------------------|")
    val numberToColumnLabelCalculator = NumberToColumnLabelCalculator()
    println(numberToColumnLabelCalculator.numberToColumnLabel(26, 1).joinToString())
    println(numberToColumnLabelCalculator.numberToColumnLabel(28, 1).joinToString())
    println(numberToColumnLabelCalculator.numberToColumnLabel(26, 3).joinToString())
    println("|--------------------- assignment 1 -----------------------------|")

    runBlocking {
        println("|--------------------- assignment 2 -----------------------------|")
        val repo = ProductRepository()
        val rawProducts = async { repo.fetchProducts() }.await()
        println("Raw Products: ")
        rawProducts.forEach { product ->
            println("SKU: ${product.SKU}, Name: ${product.name}, Price: ${product.price}, Type: ${product.type}, Image: ${product.image}, Stock: ${product.stock}")
        }

        val inventories = async { repo.fetchInventories() }.await()
        println("Inventory: ")
        inventories.forEach { inventory ->
            println("SKU: ${inventory.SKU}, Zone: ${inventory.zone}, Quantity: ${inventory.quantity}")
        }

        val productsWithProcessedStock = processingProductStock(rawProducts, inventories)
        val processedProducts = processingProductPrice(productsWithProcessedStock)
        println("Processed Products: ")
        processedProducts.forEach { product ->
            println("SKU: ${product.SKU}, Name: ${product.name}, Price: ${product.price}, Type: ${product.type}, Image: ${product.image}, Stock: ${product.stock}")
        }
        println("|--------------------- assignment 2 -----------------------------|")
    }
}
fun processingProductStock(products: List<Product>, inventories: List<Inventory>): List<Product> {
    val productsWithProcessedStock = listOf<Product>().toMutableList()
    products.forEach { product ->
        inventories.forEach {
            if (product.SKU == it.SKU) {
                product.stock += it.quantity
            }
        }
        productsWithProcessedStock.add(product)
    }
    return productsWithProcessedStock
}
fun processingProductPrice(products: List<Product>): List<Product> {
    val productsWithProcessedPrice = listOf<Product>().toMutableList()
    products.forEach { product ->
        when (product.type) {
            ProductType.NORMAL -> productsWithProcessedPrice.add(product)
            ProductType.HIGH_DEMAND -> {
                when {
                    product.stock > 100 -> productsWithProcessedPrice.add(product)
                    product.stock in 31..100 -> {
                        val processedProduct = product.copy(price = product.price * 1.2)
                        productsWithProcessedPrice.add(processedProduct)
                    }
                    else -> {
                        val processedProduct = product.copy(price = product.price * 1.5)
                        productsWithProcessedPrice.add(processedProduct)
                    }
                }
            }
        }
    }
    return productsWithProcessedPrice
}