package at.tscheppe.wresult

import at.tscheppe.wresult.Wresult.Companion.wresultOf
import at.tscheppe.wresult.Wresult.Failure
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

sealed class ExplicitFailure : Failure() {
    data object Exception : ExplicitFailure()
    data object Error : ExplicitFailure()
}

fun main() {
    val wResult = wresultOf { performAction() }

    val result = wResult
        .onSuccess { println("Success") }
        .onFailure { println("Error") }
        .onSuccessMapToWresultOf { performAction() }
        .getOrElse(false)

    println(result)

    runBlocking {
        wresultOf {
            runBlocking { 1 }
        }.onSuccessMapToWresultOf {
            runBlocking { 1 }
        }.onSuccessMapToWresultOf {
            1
        }.onFailureMapToWresultOf {
            runBlocking { 1 }
        }
    }
}


private fun performAction(): Boolean {
    val returnValue = Random.nextBoolean()
    if (returnValue) {
        return returnValue
    } else {
        throw RuntimeException()
    }
}