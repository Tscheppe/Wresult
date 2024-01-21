package at.tscheppe.wresult

import at.tscheppe.wresult.Wresult.Companion.suspendWresultOf
import at.tscheppe.wresult.Wresult.Companion.wresultOf


inline val Wresult<Any>.isFailure: Boolean
    get() = this is Wresult.Failure


inline fun <T> Wresult<T>.onFailure(
    crossinline onResultOf: () -> Unit,
): Wresult<T> {
    if (this is Wresult.Failure) {
        onResultOf()
    }
    return this
}


inline fun <T> Wresult<T>.suspendOnFailure(
    crossinline onResultOf: () -> Unit,
): Wresult<T> {
    if (this is Wresult.Failure) {
        onResultOf()
    }
    return this
}


@Suppress("UNCHECKED_CAST")
inline fun <T, reified V> Wresult<T>.mapOnFailureToWresultOf(
    crossinline mapper: () -> V,
): Wresult<V> {
    if (this is Wresult.Failure) {
        return wresultOf { mapper() }
    }
    return this as Wresult<V>
}


@Suppress("UNCHECKED_CAST")
suspend inline fun <T, reified V> Wresult<T>.suspendMapOnFailureToWresultOf(
    crossinline mapper: suspend () -> V,
): Wresult<V> {
    if (this is Wresult.Failure) {
        return suspendWresultOf { mapper() }
    }
    return this as Wresult<V>
}
