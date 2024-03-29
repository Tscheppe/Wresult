package at.tscheppe.wresult

import at.tscheppe.wresult.Wresult.Companion.suspendWresultOf
import at.tscheppe.wresult.Wresult.Companion.wresultOf

/**
 * Returns `true` if this instance represents [Success][Wresult.Success] or `false` otherwise.
 */
inline val <T> Wresult<T>.isSuccess: Boolean
    get() = this is Wresult.Success

/**
 * Executes the given [block] function if this instance represents [Success][Wresult.Success].
 * Returns the original `Wresult` unchanged.
 *
 * @param block The function that will be executed.
 * @return The original `Wresult` unchanged.
 */
inline fun <T> Wresult<T>.onSuccess(
    crossinline block: (T) -> Unit,
): Wresult<T> {
    if (this is Wresult.Success) {
        block(this.data)
    }
    return this
}

/**
 * Executes the given [block] function if this instance represents [Success][Wresult.Success].
 * Returns the original `Wresult` unchanged.
 *
 * @param block The function that will be executed.
 * @return The original `Wresult` unchanged.
 */
suspend inline fun <T> Wresult<T>.suspendOnSuccess(
    crossinline block: suspend (T) -> Unit,
): Wresult<T> {
    if (this is Wresult.Success) {
        block(this.data)
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
inline fun <T, reified V> Wresult<T>.mapOnSuccess(
    crossinline mapper: (T) -> V,
): Wresult<V> {
    if (this is Wresult.Success) {
        return wresultOf { mapper(data) }
    }
    return this as Wresult<V>
}

/**
 * Executes the given [mapper] function if this instance represents [Success][Wresult.Success].
 * Returns the result of [mapper] function as a `Wresult`.
 *
 * @param mapper The function that will be executed.
 * @return The result of [mapper] function as a `Wresult`.
 */
@Suppress("UNCHECKED_CAST")
suspend inline fun <T, reified V> Wresult<T>.suspendMapOnSuccess(
    crossinline mapper: suspend (T) -> V,
): Wresult<V> {
    if (this is Wresult.Success) {
        return suspendWresultOf { mapper(data) }
    }
    return this as Wresult<V>
}

/**
 * Executes the given [mapper] function if this instance represents [Success][Wresult.Success].
 * Returns the result of [mapper] function as a `Wresult`.
 *
 * @param mapper The function that will be executed.
 * @return The result of [mapper] function as a `Wresult`.
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, reified V> Wresult<T>.flatMapOnSuccess(
    crossinline mapper: (T) -> Wresult<V>,
): Wresult<V> {
    if (this is Wresult.Success) {
        return mapper(data)
    }
    return this as Wresult<V>
}

/**
 * Executes the given [mapper] function if this instance represents [Success][Wresult.Success].
 * Returns the result of [mapper] function as a `Wresult`.
 *
 * @param mapper The function that will be executed.
 * @return The result of [mapper] function as a `Wresult`.
 */
@Suppress("UNCHECKED_CAST")
suspend inline fun <T, reified V> Wresult<T>.suspendFlatMapOnSuccess(
    crossinline mapper: suspend (T) -> Wresult<V>,
): Wresult<V> {
    if (this is Wresult.Success) {
        return mapper(data)
    }
    return this as Wresult<V>
}
