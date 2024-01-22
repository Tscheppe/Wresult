package at.tscheppe.wresult

import at.tscheppe.wresult.Wresult.Companion.wresultOf

/**
 * Returns `true` if this instance represents [Success][Wresult.Success] or `false` otherwise.
 */
inline val Wresult<Any>.isSuccess: Boolean
    get() = this is Wresult.Success

/**
 * Executes the given [onResultOf] function if this instance represents [Success][Wresult.Success].
 * Returns the original `Wresult` unchanged.
 *
 * @param onResultOf The function that will be executed.
 * @return The original `Wresult` unchanged.
 */
inline fun <T> Wresult<T>.onSuccess(
    crossinline onResultOf: (T) -> Unit,
): Wresult<T> {
    if (this is Wresult.Success) {
        onResultOf(this.data)
    }
    return this
}

/**
 * Executes the given [mapper] function if this instance represents [Success][Wresult.Success].
 * Returns the result of [mapper] function as a `Wresult`.
 *
 * @param mapper The function that will be executed.
 * @return The result of [mapper] function as a `Wresult`.
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, reified V> Wresult<T>.onSuccessMapToWresultOf(
    crossinline mapper: (T) -> V,
): Wresult<V> {
    if (this is Wresult.Success) {
        return wresultOf { mapper(data) }
    }
    return this as Wresult<V>
}
