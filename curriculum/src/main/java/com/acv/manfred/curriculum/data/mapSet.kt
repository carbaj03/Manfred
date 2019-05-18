package com.acv.manfred.curriculum.data


fun <A, B> Set<A>.mapSet(f: (A) -> B): Set<B> =
    map { f(it) }.toSet()

fun <A, B> Set<A>.mapSetPosition(f: (A, Int) -> B): Set<B> {
    var index = 0
    return mapSet { f(it, index++) }
}
