package functions.scope_functions


fun main(){
	mutableListOf<Int>()
		.myWith{
			while(true){
				print("Enter number or 0 to exit:  ")
				val number = readln().toInt().takeIf{it != 0} ?: break
				add(number)
			}
			this
		}.forEach { println(it) }
}


inline fun <T, R> T.myWith(block: T.() -> R) : R{
	return block()
}