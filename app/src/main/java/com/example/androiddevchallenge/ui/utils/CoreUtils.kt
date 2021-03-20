package com.example.androiddevchallenge.ui.utils

import java.util.ArrayList
import java.util.Random

fun generateRandomList(size: Int, from: Int, to: Int): List<Int> {
    val list = ArrayList<Int>(size)
    for (i in 0 until size) {
        list.add(random(from = from, to = to))
    }
    return list
}

fun random(from: Int, to: Int): Int {
    return Random().nextInt(to - from) + from
}