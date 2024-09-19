package com.example.composevkclient.domain

//data class StatisticsItem(
//    val type: StatisticsType,
//    val count: Int = 0,
//)
//
enum class StatisticsType {
    VIEWS, COMMENTS, SHARES, LIKES
}

data class StatisticsItem(
    val views: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val likes: Int = 0
)