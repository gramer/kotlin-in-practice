import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Chapter4KtTest : FunSpec({

    test("isEven") {
        forAll(
            row(4, "4 is even"),
            row(2, "2 is even"),
        ) { n, expected ->
            isEven(n).shouldBe(expected)
        }
    }

    test("partial1") {
        forAll(
            row({ a: String, b: String -> a + b }, "hello", "world", "helloworld")
        ) { func, p1, p2, expected ->
            func.partial1(p1)(p2).shouldBe(expected)
        }
    }

    test("partial3") {
        forAll(
            row({ a: String, b: String, c: String -> "$a$b $c" }, "hello", "world", "gramer", "helloworld gramer")
        ) { func, p1, p2, p3, expected ->
            func.partial3(p1, p2)(p3).shouldBe(expected)
        }
    }

    test("multiThree") {
        multiThree(1)(2)(3).shouldBe(6)
    }

    test("max") {
        max(1)(2).shouldBe(2)
        max(2)(2).shouldBe(2)
        max(3)(-1).shouldBe(3)
    }

    test("minCurried") {
        val min = { a: Int, b: Int -> if (a > b) b else a }
        min.curried()(1)(2).shouldBe(1)
    }

    test("compose") {
        val addThree = { i: Int -> i + 3 }
        val twice = { i: Int -> i * 2 }

        val func = addThree compose twice
        func(3).shouldBe(9)
    }

})