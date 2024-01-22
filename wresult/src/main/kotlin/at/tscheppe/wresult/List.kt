package at.tscheppe.wresult

/**
 * Returns `true` if all elements are instances of [Wresult.Success] or `false` otherwise.
 */
fun <T> List<Wresult<T>>.allSuccess(): Boolean {
    return this.all { it.isSuccess }
}

/**
 * Returns `true` if any elements are instances of [Wresult.Success] or `false` otherwise.
 */
fun <T> List<Wresult<T>>.anySuccess(): Boolean {
    return this.any { it.isSuccess }
}

/**
 * Returns a list containing only the [Wresult.Success] elements.
 */
fun <T> List<Wresult<T>>.filterSuccess(): List<Wresult.Success<T>> {
    return this.filter { it.isSuccess }.map { it as Wresult.Success }
}

/**
 * Returns a list containing only the [Wresult.Failure] elements.
 */
fun <T> List<Wresult<T>>.filterFailure(): List<Wresult.Failure<T>> {
    return this.filter { it.isFailure }.map { (it as Wresult.Failure) }
}

/**
 * Returns `true` if all elements are instances of [Wresult.Failure] or `false` otherwise.
 */
fun <T> List<Wresult<T>>.allFailure(): Boolean {
    return this.all { it.isFailure }
}

/**
 * Returns `true` if any elements are instances of [Wresult.Failure] or `false` otherwise.
 */
fun <T> List<Wresult<T>>.anyFailure(): Boolean {
    return this.any { it.isFailure }
}

/**
 * Returns a pair of lists, where first list contains only [Wresult.Success] elements and second list contains only [Wresult.Failure] elements.
 */
fun <T> List<Wresult<T>>.partition(): Pair<List<Wresult.Success<T>>, List<Wresult.Failure<T>>> {
    return Pair(this.filterSuccess(), this.filterFailure())
}