package com.acv.manfred.curriculum.data


fun <A, B> Set<A>.mapSet(f: (A) -> B): Set<B> =
    map { f(it) }.toSet()