package com.example.composevkclient.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composevkclient.domain.FeedPost
import com.example.composevkclient.domain.StatisticsItem

@Composable
fun PostCard(
    feedPost: FeedPost,
    modifier: Modifier = Modifier,
    onViewsClickListener: () -> Unit,
    onShareClickListener: () -> Unit,
    onCommentClickListener: () -> Unit,
    onLikeClickListener: () -> Unit
) {
    Log.d("RECOMPOSITION", "PostCard")
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader(
                avatar = feedPost.avatarResId,
                publicationDate = feedPost.publicationDate,
                communityName = feedPost.communityName
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedPost.contentText
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(
                    id = feedPost.contentImageResId
                ),
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            PostCardStatistics(
                feedPost = feedPost,
                onViewsClickListener = onViewsClickListener,
                onShareClickListener = onShareClickListener,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = onLikeClickListener
            )
        }
    }
}

@Composable
fun PostHeader(
    avatar: Int,
    publicationDate: String,
    communityName: String
) {
    Log.d("RECOMPOSITION", "PostHeader")
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),
            painter = painterResource(id = avatar),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = communityName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = publicationDate,
                color = MaterialTheme.colorScheme.onSecondary
            )

        }
        Icon(
            modifier = Modifier,
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}