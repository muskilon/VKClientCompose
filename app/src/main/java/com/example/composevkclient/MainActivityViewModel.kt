package com.example.composevkclient

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composevkclient.domain.FeedPost
import com.example.composevkclient.domain.StatisticsItem
import com.example.composevkclient.domain.StatisticsType
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {
    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(
                FeedPost(
                    id = it,
                    communityName = "$it /dev/null",
                    publicationDate = "${Random.nextInt(24)}:${Random.nextInt(60)}",
                    avatarResId = R.drawable.avatar,
                    contentImageResId = R.drawable.post_image,
                    statistics = StatisticsItem()

                )
            )
        }
    }

    private val _models = mutableStateOf(initialList as List<FeedPost>)
    val models = _models as State<List<FeedPost>>

    fun increaseCount(model: FeedPost, typeStatistics: StatisticsType) {
        val modifiedList = models.value.toMutableList()
        val index = modifiedList.indexOf(model)
        modifiedList[index] = model.copy(statistics = (with(model.statistics) {
            when (typeStatistics) {
                StatisticsType.VIEWS -> copy(views = model.statistics.views + 1)

                StatisticsType.COMMENTS -> copy(comments = model.statistics.comments + 1)

                StatisticsType.LIKES -> copy(likes = model.statistics.likes + 1)

                StatisticsType.SHARES -> copy(shares = model.statistics.shares + 1)
            }
        }
                )
        )
        _models.value = modifiedList
    }

    fun delete(model: FeedPost) {
        val modifiedList = models.value.toMutableList()
        modifiedList.remove(model)
        _models.value = modifiedList
    }
}