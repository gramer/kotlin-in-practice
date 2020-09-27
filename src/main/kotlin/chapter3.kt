fun factorial(n: Int): Int = when (n) {
    0 -> 0
    1 -> 1
    else -> n * factorial(n - 1)
}

var facMemo = Array(100) { -1 }
fun factorialWithMemoization(n: Int): Int = when {
    n == 0 -> 0
    n == 1 -> 1
    facMemo[n] != -1 -> facMemo[n]
    else -> {
        facMemo[n] = n * factorial(n - 1)
        facMemo[n]
    }
}

tailrec fun factorialFP(n: Int, acc: Int = 1): Int = when (n) {
    0 -> 0
    1 -> acc
    else -> factorialFP(n - 1, acc * n)
}

fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)

fun maximum(nums: List<Int>): Int = when {
    nums.isNullOrEmpty() -> error("not empty")
    nums.size == 1 -> nums.first()
    else -> {
        val head = nums.head()
        val tail = nums.tail()
        val maxVal = maximum(tail)
        if (head > maxVal) head else maxVal
    }
}

tailrec fun maximumFP(nums: List<Int>, accMaxVal: Int = Integer.MIN_VALUE): Int = when {
    nums.isNullOrEmpty() -> error("not empty")
    nums.size == 1 -> if (nums.first() > accMaxVal) nums.first() else accMaxVal
    else -> {
        val head = nums.head()
        val tail = nums.tail()
        maximumFP(tail, if (head > accMaxVal) head else accMaxVal)
    }
}

fun String.head() = first()
fun String.tail() = drop(1)

fun reverse(s: String): String = when {
    s.isEmpty() -> ""
    s.length == 1 -> s
    else -> reverse(s.tail()) + s.head()
}

tailrec fun reverse(s: String, acc: String = ""): String = when {
    s.isEmpty() -> acc
    else -> reverse(s.tail(), s.head() + acc)
}

fun fibonacci(n: Int): Int = when (n) {
    0 -> 0
    1 -> 1
    else -> fibonacci(n - 1) + fibonacci(n - 2)
}

var memo = Array(100) { -1 }
fun fibWithMemoization(n: Int): Int = when {
    n == 0 -> 0
    n == 1 -> 1
    memo[n] != -1 -> memo[n]
    else -> {
        memo[n] = fibWithMemoization(n - 1) + fibWithMemoization(n - 2)
        memo[n]
    }
}

tailrec fun fibFP(n: Int, first: Int = 0, second: Int = 1): Int = when (n) {
    0 -> first
    1 -> second
    else -> fibFP(n - 1, second, first + second)
}

fun replicate(n: Int, element: Int): List<Int> = when (n) {
    0 -> listOf()
    1 -> listOf(element)
    else -> listOf(element) + replicate(n - 1, element)
}

tailrec fun replicateFP(n: Int, element: Int, acc: List<Int> = listOf()): List<Int> = when (n) {
    0 -> listOf()
    1 -> acc + element
    else -> replicateFP(n - 1, element, acc + element)
}

fun elem(n: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    else -> (n == list.head()) || elem(n, list.tail())
}

fun gcd(m: Int, n: Int): Int {
    val r: Int = m % n
    return if (r == 0) n else gcd(n, r)
}

tailrec fun power(n: Int, exp: Int, acc: Int = n): Int = when {
    n == 0 -> 0
    exp == 0 -> 1
    exp == 1 -> acc
    else -> power(n, exp - 1, n * acc)
}

fun <T> Set<T>.head() = first()
fun <T> Set<T>.tail() = drop(1).toSet()

fun <T> powerset(s: Set<T>): Set<Set<T>> = when {
    s.isEmpty() -> setOf(setOf())
    else -> {
        val head = s.head()
        val restSet = powerset(s.tail())
        restSet + restSet.map { setOf(head) + it }.toSet()
    }
}

fun <T> powersetFP(s: Set<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
    s.isEmpty() -> acc
    else -> powersetFP(s.tail(), acc + acc.map { it + s.head() })
}