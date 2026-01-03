package collections.List

interface MyMutableLinkedList {

	val size: Int

	fun add(element: Int)

	fun remove(element: Int)

	fun size(): Int

	fun indexOf(element: Int): Int

	fun get(index: Int): Int

}

class Node(
	var data: Int,
	var next: Node? = null
)

class MyLinkedList : MyMutableLinkedList {

	private var head: Node? = null

	private var tail: Node? = null

	private var count: Int = 0

	override val size: Int
		get() = count

	override fun add(element: Int) {
		val newNode = Node(element)

		if (head == null) {
			head = newNode
			tail = newNode
		} else {
			tail?.next = newNode
			tail = newNode
		}

		count++
	}

	override fun remove(element: Int) {
		if (head == null) {
			return
		}
		if (head?.data == element) {
			head = head?.next
			count--
			if (head == null) {
				tail = null
			}
			return
		}

		var current = head
		while (current?.next != null) {
			if (current.next?.data == element) {
				current.next = current.next?.next
				count--
				if (current.next == null) {
					tail = current
				}
				return
			}
			current = current.next
		}
	}

	override fun size(): Int {
		return count
	}

	override fun indexOf(element: Int): Int {
		var current = head
		var index = 0

		while (current != null) {
			if (current.data == element) {
				return index
			}
			current = current.next
			index++
		}

		return -1
	}

	override fun get(index: Int): Int {
		if (index < 0 || index >= count) {
			throw IndexOutOfBoundsException("Index $index out of bounds for size $count")
		}

		var current = head
		var currentIndex = 0

		while (currentIndex < index) {
			current = current?.next
			currentIndex++
		}

		return current!!.data
	}
}