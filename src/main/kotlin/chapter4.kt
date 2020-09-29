class PartialFunction<P, R>(
    private val condition: (P) -> Boolean,
    private val f: (P) -> R
) : (P) -> R {
    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported")
    }

    fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(p: P, default: R): R = if (isDefinedAt(p)) invoke(p) else default

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
        PartialFunction(
            { this.isDefinedAt(it) || that.isDefinedAt(it) },
            {
                when {
                    this.isDefinedAt(it) -> this(it)
                    that.isDefinedAt(it) -> that(it)
                    else -> throw IllegalArgumentException("$it isn't supported")
                }
            })
}

fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean):
        PartialFunction<P, R> = PartialFunction(definedAt, this)

val condition: (Int) -> Boolean = { it.rem(2) == 0 }
val body: (Int) -> String = { "$it is even" }
val isEven = body.toPartialFunction(condition)

fun <P1, P2, R> ((P1, P2) -> R).partial1(p1: P1): (P2) -> R = { p2 -> this(p1, p2) }
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p1: P1, p2: P2): (P3) -> R = { p3 -> this(p1, p2, p3) }

fun multiThree(a: Int) = { b: Int -> { c: Int -> a * b * c } }
fun max(a: Int) = { b: Int -> if (a > b) a else b }

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R =
    { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }

fun <P1, P2, P3, R> ((P1) -> (P2) -> (P3) -> R).uncurried(): (P1, P2, P3) -> R =
    { p1: P1, p2: P2, p3: P3 -> this(p1)(p2)(p3) }

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
    { p1: P1 -> { p2: P2 -> this(p1, p2) } }

infix fun <F,G,R> ((F) -> R).compose(g: (G)-> F): (G) -> R = { gInput:G -> this(g(gInput)) }