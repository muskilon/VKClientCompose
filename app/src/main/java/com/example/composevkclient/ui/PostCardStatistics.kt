package com.example.composevkclient.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.composevkclient.domain.FeedPost
import com.example.composevkclient.domain.StatisticsItem
import com.example.composevkclient.domain.StatisticsType

@Composable
fun PostCardStatistics(
    feedPost: FeedPost,
    onViewsClickListener: () -> Unit,
    onShareClickListener: () -> Unit,
    onCommentClickListener: () -> Unit,
    onLikeClickListener: () -> Unit
) {
    Log.d("RECOMPOSITION", "PostCardStatistics")
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(
                iconResId = Icons.Rounded.Search,
                text = feedPost.statistics.views.toString(),
                onItemClickListener = { onViewsClickListener() }
            )
        }
        Row (
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconWithText(
                iconResId = Icons.Rounded.Refresh,
                text = feedPost.statistics.shares.toString(),
                onItemClickListener = { onShareClickListener() }
            )
            IconWithText(
                iconResId = Icons.Rounded.MailOutline,
                text = feedPost.statistics.comments.toString(),
                onItemClickListener = { onCommentClickListener() }
            )
            IconWithText(
                iconResId = Icons.Default.FavoriteBorder,
                text = feedPost.statistics.likes.toString(),
                onItemClickListener = { onLikeClickListener() }
            )
        }
    }
}

@Composable
private fun IconWithText(
    iconResId: ImageVector,
    text: String,
    onItemClickListener: () -> Unit
) {
    Log.d("RECOMPOSITION", "IconWithText")
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = iconResId,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
            )
    }
}