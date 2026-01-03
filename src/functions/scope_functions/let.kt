package functions.scope_functions

import kotlin.io.println

var age: Int? = 24

fun main(){
	val result = age?.myLet{
		if (it >= 18){
			"You're an adult"
		}
		else{
			"You're a child, you will be adult in ${18 - it} years"
		}
	}

	result?.myLet { println(it) }
}

// let можно вызвать у любого объекта, от лямбды мы можем получить результат любого типа


inline fun <T, R> T.myLet(block: (T) -> R) : R{
	return block(this)
}