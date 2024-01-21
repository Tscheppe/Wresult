package at.tscheppe.wresult

inline fun <T> Wresult<T>.getOrElse(crossinline defaultValue: () -> T): T {
    return when (this) {
        is Wresult.Success -> data
        is Wresult.Failure -> defaultValue()
    }
}


fun <T> Wresult<T>.getOrElse(defaultValue: T): T {
    return when (this) {
        is Wresult.Success -> data
        is Wresult.Failure -> defaultValue
    }
}


fun <T> Wresult<T>.getOrNull(): T? {
    return when (this) {
        is Wresult.Success -> data
        is Wresult.Failure -> null
    }
}
