import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

interface Box<T>
open class Language
open class JVM : Language()
open class Kotlin : JVM()

fun box(test: Box<out JVM>) {
    println(test)
}

class Chapter2KtTest : FunSpec({

    test("string extension") {
        "world".hello().shouldBe("Hello, world")
    }

    test("lambda receiver") {
        val stringToInt: String.() -> Int = { toInt() }
        stringToInt("3").shouldBe(3)
    }

    test("variance") {
        val langBox = object : Box<Language> {}
        val kotlinBox = object : Box<Kotlin> {}
        val jvmBox = object : Box<JVM> {}

        box(jvmBox)
        box(kotlinBox)
//        box(langBox) // compile error
    }

})
