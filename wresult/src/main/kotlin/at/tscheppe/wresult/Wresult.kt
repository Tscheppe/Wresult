package at.tscheppe.wresult


sealed interface Wresult<T> {
    data class Success<T>(val data: T) : Wresult<T>
    open class Failure : Wresult<Nothing>

    companion object {

        @Suppress("UNCHECKED_CAST")
        inline fun <reified T> wresultOf(crossinline provider: () -> T): Wresult<T> {
            return try {
                val result = provider()
                Success(result)
            } catch (e: Throwable) {
                Failure() as Wresult<T>
            }
        }

        @Suppress("UNCHECKED_CAST")
        suspend inline fun <reified T> suspendWresultOf(crossinline provider: suspend () -> T): Wresult<T> {
            return try {
                val result = provider()
                Success(result)
            } catch (e: Throwable) {
                Failure() as Wresult<T>
            }
        }
    }
}
