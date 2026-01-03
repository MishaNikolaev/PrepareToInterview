package collections.Set

import kotlin.math.abs

interface MutableSet {

	val size: Int

	fun add(element: Int)

	fun remove(element: Int)

	fun clear()

	fun contains(element: Int): Boolean

	companion object {
		const val INITIAL_CAPACITY = 16
		const val LOAD_FACTOR = 0.75
	}
}

class Node(
	var item: Int,
	var next: Node? = null
)

class HashSet : MutableSet {

	private var buckets: Array<Node?> = arrayOfNulls(MutableSet.INITIAL_CAPACITY)

	override var size: Int = 0
		private set

	private fun getBucketIndex(element: Int, arraySize: Int): Int {
		return abs(element.hashCode() % arraySize)
	}

	override fun add(element: Int) {
		if (size >= buckets.size * MutableSet.LOAD_FACTOR) {
			resize()
		}

		val bucketIndex = getBucketIndex(element, buckets.size)
		val head = buckets[bucketIndex]

		if (head == null) {
			buckets[bucketIndex] = Node(element)
			size++
			return
		}

		var current = head
		while (current != null) {
			if (current.item == element) {
				return
			}
			if (current.next == null) {
				current.next = Node(element)
				size++
				return
			}
			current = current.next
		}
	}

	override fun remove(element: Int) {
		val bucketIndex = getBucketIndex(element, buckets.size)
		val head = buckets[bucketIndex]

		if (head == null) {
			return
		}

		if (head.item == element) {
			buckets[bucketIndex] = head.next
			size--
			return
		}

		var current = head
		while (current?.next != null) {
			if (current.next?.item == element) {
				current.next = current.next?.next
				size--
				return
			}
			current = current.next!!
		}
	}

	override fun clear() {
		buckets = arrayOfNulls(MutableSet.INITIAL_CAPACITY)
		size = 0
	}

	override fun contains(element: Int): Boolean {
		val bucketIndex = getBucketIndex(element, buckets.size)
		val head = buckets[bucketIndex]

		var current = head
		while (current != null) {
			if (current.item == element) {
				return true
			}
			current = current.next
		}

		return false
	}

	private fun resize() {
		val oldBuckets = buckets
		val newCapacity = buckets.size * 2
		buckets = arrayOfNulls(newCapacity)
		size = 0

		for (head in oldBuckets) {
			var current = head
			while (current != null) {
				add(current.item)
				current = current.next
			}
		}
	}
}