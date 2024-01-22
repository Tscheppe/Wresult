package at.tscheppe.wresult

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Wresult] are either an instance of [Success] or [Failure].
 * FP inspired by Kotlin Result
 *
 * @see Success
 * @see Failure
 */
sealed interface Wresult<T> {
    /**
     * Represents the success side of [Wresult] class which by convention is a "Success".
     * @param data The encapsulated data of successful result.
     */
    data class Success<T>(val data: T) : Wresult<T>

    /**
     * Represents the failure side of [Wresult] class which by convention is a "Failure".
     */
    open class Failure : Wresult<Nothing>

    companion object {
        /**
         * Executes the given provider function and returns its encapsulated result value as a Wresult.Success.
         * Returns a Wresult.Failure if an exception is thrown.
         *
         * @param provider The provider function that will be invoked.
         * @return A Wresult.Success encapsulating the result of invoking the given provider function,
         * or a Wresult.Failure if an exception is thrown.
         */
        @Suppress("UNCHECKED_CAST")
        inline fun <reified T> wresultOf(crossinline provider: () -> T): Wresult<T> {
            return try {
                val result = provider()
                Success(result)
            } catch (e: Throwable) {
                Failure() as Wresult<T>
            }
        }
    }
}

