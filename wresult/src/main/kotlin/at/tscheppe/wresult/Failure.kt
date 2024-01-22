package at.tscheppe.wresult

import at.tscheppe.wresult.Wresult.Companion.wresultOf

/**
 * Returns `true` if this instance represents [Failure][Wresult.Failure] or `false` otherwise.
 */
inline val Wresult<Any>.isFailure: Boolean
    get() = this is Wresult.Failure

/**
 * Executes the given [onResultOf] function if this instance represents [Failure][Wresult.Failure].
 * Returns the original `Wresult` unchanged.
 *
 * @param onResultOf The function that will be executed.
 * @return The original `Wresult` unchanged.
 */
inline fun <T> Wresult<T>.onFailure(
    crossinline onResultOf: () -> Unit,
): Wresult<T> {
    if (this is Wresult.Failure) {
        onResultOf()
    }
    return this
}

/**
 * Executes the given [mapper] function if this instance represents [Failure][Wresult.Failure].
 * Returns the result of [mapper] function as a `Wresult`.
 *
 * @param mapper The function that will be executed.
 * @return The result of [mapper] function as a `Wresult`.
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, reified V> Wresult<T>.onFailureMapToWresultOf(
    crossinline mapper: () -> V,
): Wresult<V> {
    if (this is Wresult.Failure) {
        return wresultOf { mapper() }
    }
    return this as Wresult<V>
}

