package nl.bjornvanderlaan

object FuzzyFactorial {
    fun of(num: Long) : Long {
        val fac = factorial(num)
        return fac + (((1..20).random() / 100.0) * fac).toLong()
    }

    private tailrec fun factorial(n: Long, acc: Long = 1): Long {
        val res = n * acc
        return if (n <= 1) {
            res
        } else {
            factorial(n - 1, res)
        }
    }
}