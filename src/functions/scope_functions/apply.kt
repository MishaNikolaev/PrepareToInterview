package functions.scope_functions

fun main(){
	mutableListOf<Int>().myApply{
		while(true){
			print("Enter number or 0 to exit:  ")
			val number = readln().toInt().takeIf{it != 0} ?: break
			add(number)
		}
	}.forEach { println(it) }
}

inline fun <T> T.myApply(block: T.() -> Unit) : T{
	block()
	return this
}