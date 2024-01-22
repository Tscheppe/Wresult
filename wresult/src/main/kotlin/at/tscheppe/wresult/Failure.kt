package at.tscheppe.wresult

import at.tscheppe.wresult.Wresult.Companion.wresultOf

/**
 * Returns `true` if this instance represents [Failure][Wresult.Failure] or `false` otherwise.
 */
inline val <T> Wresult<T>.isFailure: Boolean
    get() = this is Wresult.Failure

/**
 * Executes the given [block] function if this instance represents [Failure][Wresult.Failure].
 * Returns the original `Wresult` unchanged.
 *
 * @param block The function that will be executed.
 * @return The original `Wresult` unchanged.
 */
inline fun <T> Wresult<T>.onFailure(
    crossinline block: (Throwable?) -> Unit,
): Wresult<T> {
    if (this is Wresult.Failure) {
        block(throwable)
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
inline fun <T, reified V> Wresult<T>.mapOnFailure(
    crossinline mapper: (Throwable?) -> V,
): Wresult<V> {
    if (this is Wresult.Failure) {
        return wresultOf { mapper(throwable) }
    }
    return this as Wresult<V>
}

/**
 * Executes the given [mapper] function if this instance represents [Failure][Wresult.Failure].
 * Returns the result of [mapper] function as a `Wresult`.
 *
 * @param mapper The function that will be executed.
 * @return The result of [mapper] function as a `Wresult`.
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, V> Wresult<T>.flatMapOnFailure(
    crossinline mapper: (Throwable?) -> Wresult<V>,
): Wresult<V> {
    if (this is Wresult.Failure) {
        return mapper(throwable)
    }
    return this as Wresult<V>
}

