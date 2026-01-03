package functions.scope_functions

data class User(
	var name: String,
	var age: Int,
	var email: String? = null
)

inline fun <T> T.myAlso(block: (T) -> Unit) : T{
	block(this)
	return this // отличие от let
}

fun main() {
	val user = User("Алексей", 25).myAlso{
		it.email = "alexey@example.com"
		println("Создан пользователь: $it")
	}

	println("Основной объект: $user")
	println("Email: ${user.email}")

}

// работает как let, но за одним исключением:
// функция let возвращает последнее значение, которое мы указываем в лямбда выражении
// а also всегда возвращает тот объект, с которым работает (тот же объект возвращает)