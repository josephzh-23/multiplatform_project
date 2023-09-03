import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val name: String,
    val email: String,
    val faculty: String,
    val year: Int,
)