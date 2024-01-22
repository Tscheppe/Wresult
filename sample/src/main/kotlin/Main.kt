package at.tscheppe.wresult

import at.tscheppe.wresult.Wresult.Companion.wresultOf
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    val wResult = wresultOf { performAction() }
    val result = wResult
        .onSuccess { println("Success") }
        .onFailure { println("Error") }
        .mapOnSuccess { performAction() }
        .getOrElse(false)

    println(result)

    runBlocking {
        wresultOf {
            runBlocking { 1 }
        }.mapOnSuccess {
            runBlocking { 1 }
        }.mapOnSuccess {
            1
        }.mapOnFailure {
            runBlocking { 1 }
        }
    }

    wresultOf { performAction() }
        .flatMapOnSuccess {
            wresultOf { performAction() }
        }.flatMapOnSuccess {
            wresultOf { performAction() }
        }.flatMapOnFailure {
            when (it) {
                is RuntimeException -> wresultOf { performAction() }
                else -> wresultOf { performAction() }
            }
        }.getOrNull().also {
            println(it)
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