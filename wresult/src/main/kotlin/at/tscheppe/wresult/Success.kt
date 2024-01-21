package at.tscheppe.wresult

import at.tscheppe.wresult.Wresult.Companion.suspendWresultOf
import at.tscheppe.wresult.Wresult.Companion.wresultOf


inline val Wresult<Any>.isSuccess: Boolean
    get() = this is Wresult.Success


inline fun <T> Wresult<T>.onSuccess(
    crossinline onResultOf: (T) -> Unit,
): Wresult<T> {
    if (this is Wresult.Success) {
        onResultOf(this.data)
    }
    return this
}


suspend inline fun <T> Wresult<T>.suspendOnSuccess(
    crossinline onResultOf: suspend (T) -> Unit,
): Wresult<T> {
    if (this is Wresult.Success) {
        onResultOf(this.data)
    }
    return this
}


@Suppress("UNCHECKED_CAST")
inline fun <T, reified V> Wresult<T>.mapOnSuccessToWresultOf(
    crossinline mapper: (T) -> V,
): Wresult<V> {
    if (this is Wresult.Success) {
        return wresultOf { mapper(data) }
    }
    return this as Wresult<V>
}


@Suppress("UNCHECKED_CAST")
suspend inline fun <T, reified V> Wresult<T>.suspendMapOnSuccessToWresultOf(
    crossinline mapper: suspend (T) -> V,
): Wresult<V> {
    if (this is Wresult.Success) {
        return suspendWresultOf { mapper(data) }
    }
    return this as Wresult<V>
}
