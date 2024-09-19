package com.example.composevkclient.domain

import com.example.composevkclient.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.avatar,
    val contentText: String = "Текст поста. Всякие тексты, сообщение и все такое. Смотрите какая картинка!",
    val contentImageResId: Int = R.drawable.post_image,
    val statistics: StatisticsItem = StatisticsItem(
        views = 966,
        comments = 7,
        shares = 8,
        likes = 27
    )
)
