package com.example.newswithcleancode.utils

import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

// This long is in millisecond, not second.
fun Long.formatDate(): String = LocalDateTime
    .ofEpochSecond(this / 1000, 0, ZoneId.systemDefault() as ZoneOffset?)
    .formatDate()

fun LocalDateTime.formatDate(): String =
    atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z"))

fun SearchRange.toDatePair(
    customEpochSecond: Long = 0 // Second, not millisecond. Beware.
) = with (LocalDateTime.now()) {
    Pair(
        formatDate(),
        when (this@toDatePair) {
            SearchRange.DAY -> minusDays(1)
            SearchRange.WEEK -> minusDays(7)
            SearchRange.MONTH -> withDayOfMonth(1)
            SearchRange.QUARTER -> LocalDate.of(year, month.value.roundToQuarterMonth() ,1).atStartOfDay()
            SearchRange.YEAR -> withDayOfYear(1)
            SearchRange.CUSTOM -> LocalDateTime.ofEpochSecond(customEpochSecond, 0, ZoneId.systemDefault() as ZoneOffset?)
        }.formatDate()
    )
}

// Int here is the month
fun Int.roundToQuarterMonth() = when(this) {
    in(4..6) -> 4
    in(7..9) -> 7
    in(10..12) -> 10
    else -> 1
}