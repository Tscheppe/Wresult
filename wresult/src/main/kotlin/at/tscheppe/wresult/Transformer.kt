package at.tscheppe.wresult

/**
 * Returns the encapsulated value if this instance represents [Success][Wresult.Success] or the
 * result of [defaultValue] function if it is [Failure][Wresult.Failure].
 *
 * @param defaultValue The default value provider function.
 * @return The encapsulated value or result of [defaultValue] function.
 */
inline fun <T> Wresult<T>.getOrElse(crossinline defaultValue: () -> T): T {
    return when (this) {
        is Wresult.Success -> data
        is Wresult.Failure -> defaultValue()
    }
}

/**
 * Returns the encapsulated value if this instance represents [Success][Wresult.Success] or the
 * [defaultValue] if it is [Failure][Wresult.Failure].
 *
 * @param defaultValue The default value.
 * @return The encapsulated value or the [defaultValue].
 */
fun <T> Wresult<T>.getOrElse(defaultValue: T): T {
    return when (this) {
        is Wresult.Success -> data
        is Wresult.Failure -> defaultValue
    }
}

/**
 * Returns the encapsulated value if this instance represents [Success][Wresult.Success] or `null`
 * if it is [Failure][Wresult.Failure].
 *
 * @return The encapsulated value or `null`.
 */
fun <T> Wresult<T>.getOrNull(): T? {
    return when (this) {
        is Wresult.Success -> data
        is Wresult.Failure -> null
    }
}
