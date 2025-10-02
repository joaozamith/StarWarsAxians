package com.example.starwarsaxians.data.helpers

fun String.extractId(): String = trimEnd('/').substringAfterLast('/')
