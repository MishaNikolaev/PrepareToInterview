package collections.List

fun main(){
	val array = MyMutableListOf()

	array.add(10)
	array.add(20)
	array.add(30)
	array.add(40)
	array.add(50)

	for (i in 0 until array.size) {
		println("   array[$i] = ${array.get(i)}")
	}
	println("\nРазмер списка: ${array.size}")
	
	println("\nПоиск элементов:")
	println("   indexOf(30) = ${array.indexOf(30)}")
	println("   indexOf(100) = ${array.indexOf(100)} (не найден)")
	
	println("\nПолучение элементов по индексу:")
	println("   array[0] = ${array.get(0)}")
	println("   array[2] = ${array.get(2)}")
	
	array.remove(30)
	
	println("\nЭлементы после удаления:")
	for (i in 0 until array.size) {
		println("   array[$i] = ${array.get(i)}")
	}
	println("   Новый размер: ${array.size}")
	
	for (i in 60..100 step 10) {
		array.add(i)
	}
	println("   Размер после добавления: ${array.size}")
	println("   Последние 3 элемента:")
	for (i in array.size - 3 until array.size) {
		println("   array[$i] = ${array.get(i)}")
	}
}

interface MyMutableList{

	val size: Int

	fun add(element: Int)

	fun remove(element: Int)

	fun size(): Int

	fun indexOf(element: Int): Int

	fun get(index: Int): Int

}

class MyMutableListOf : MyMutableList {

	private var array: Array<Int?> = arrayOfNulls(10)
	
	private var count: Int = 0
	
	override val size: Int
		get() = count

	override fun add(element: Int) {
		if (count >= array.size) {
			val newSize = array.size * 2
			val newArray: Array<Int?> = arrayOfNulls(newSize)
			
			for (i in 0 until count) {
				newArray[i] = array[i]
			}
			
			array = newArray
		}
		
		array[count] = element
		count++
	}

	override fun remove(element: Int) {
		val index = indexOf(element)
		
		if (index == -1) {
			return
		}

		for (i in index until count - 1) {
			array[i] = array[i + 1]
		}
		
		array[count - 1] = null
		
		count--
	}

	override fun size(): Int {
		return count
	}

	override fun indexOf(element: Int): Int {
		for (i in 0 until count) {
			if (array[i] == element) {
				return i
			}
		}
		return -1
	}

	override fun get(index: Int): Int {

		if (index < 0 || index >= count) {
			throw IndexOutOfBoundsException("Index $index out of bounds for size $count")
		}
		return array[index]!!
	}

}
