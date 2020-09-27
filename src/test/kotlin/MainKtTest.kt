import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain

class MainKtTest : FunSpec({

    test("main") {
        "substring".shouldContain("str")
    }
})
