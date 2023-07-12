package com.example.newswithcleancode.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun Long.formatDate(): String = LocalDateTime
    .ofEpochSecond(this * 1000, 0, ZoneId.systemDefault() as ZoneOffset?)
    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z"))