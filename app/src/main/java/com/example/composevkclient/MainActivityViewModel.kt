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
        repeat(100) {
            add(
                FeedPost(
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
        val modifiedList = models.value.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem == model) {
                    when (typeStatistics) {
                        StatisticsType.VIEWS -> {
                            oldItem.copy(
                                statistics = oldItem.statistics.copy(
                                    views = oldItem.statistics.views + 1
                                )
                            )
                        }

                        StatisticsType.COMMENTS -> {
                            oldItem.copy(
                                statistics = oldItem.statistics.copy(
                                    comments = oldItem.statistics.comments + 1
                                )
                            )
                        }

                        StatisticsType.SHARES -> {
                            oldItem.copy(
                                statistics = oldItem.statistics.copy(
                                    shares = oldItem.statistics.shares + 1
                                )
                            )
                        }

                        StatisticsType.LIKES -> {
                            oldItem.copy(
                                statistics = oldItem.statistics.copy(
                                    likes = oldItem.statistics.likes + 1
                                )
                            )
                        }
                    }
                } else {
                    oldItem
                }
            }
        }
        _models.value = modifiedList
    }

    fun delete(model: FeedPost) {
        val modifiedList = models.value.toMutableList()
        modifiedList.remove(model)
        _models.value = modifiedList
    }
}