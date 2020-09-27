import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class Chapter3KtTest : FunSpec({

    test("factorial") {
        forAll(
            row(5, 120),
        ) { n, expected ->
            factorial(n).shouldBe(expected)
            factorialWithMemoization(n).shouldBe(expected)
            factorialFP(n).shouldBe(expected)
        }
    }

    test("maximum") {
        forAll(
            row(listOf(1, 2, 3, 4, 5), 5),
            row(listOf(1, 2, 6, 4, 5), 6),
        ) { nums, expected ->
            maximum(nums).shouldBe(expected)
            maximumFP(nums).shouldBe(expected)
        }
    }

    test("reverse") {
        forAll(
            row("abcd", "dcba"),
        ) { s, expected ->
            reverse(s).shouldBe(expected)
        }
    }

    test("fibonacci") {
        forAll(
            row(1, 1),
            row(0, 0),
            row(5, 5),
        ) { n, expected ->
            fibonacci(n).shouldBe(expected)
            fibWithMemoization(n).shouldBe(expected)
            fibFP(n).shouldBe(expected)
        }
    }

    test("replicate") {
        forAll(
            row(3, 5, listOf(5, 5, 5)),
        ) { n, element, expected ->
            replicate(n, element).shouldBe(expected)
            replicateFP(n, element).shouldBe(expected)
        }
    }

    test("elem") {
        forAll(
            row(3, listOf(1, 3, 5), true),
            row(3, listOf(1, 2, 5), false),
        ) { n, list, expected ->
            elem(n, list).shouldBe(expected)
        }
    }

    test("gcd") {
        forAll(
            row(12, 18, 6),
            row(18, 12, 6),
        ) { m, n, expected ->
            gcd(m, n).shouldBe(expected)
        }
    }

    test("power") {
        forAll(
            row(2, 3, 8)
        ) { m, exp, expected ->
            power(m, exp).shouldBe(expected)
        }
    }

    test("powerSet") {
        forAll(
            row(setOf(1,2), setOf(setOf(1), setOf(2), setOf(1,2), setOf())),
        ) { m, expected ->
            powerset(m).shouldContainAll(expected)
        }
    }
})