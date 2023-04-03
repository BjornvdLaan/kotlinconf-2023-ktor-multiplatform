package nl.bjornvanderlaan

import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
data class Cat(val id: Long, val name: String, val breed: String, val luckyNumber: Long = 7)

class CatRepository {
    val cats = listOf(
        Cat(0, "Kitty", "Siamese Cat"),
        Cat(1, "Mr Chief", "Maine Coon"),
        Cat(2, "Bob", "Burmese Cat"),
        Cat(3, "Kevin", "Sphynx"),
        Cat(4, "Iris", "Ragdoll")
    )
        .associateBy { it.id }

    suspend fun findById(id: Long): Cat? {
        delay(200)
        return cats[id % cats.size]
    }
}