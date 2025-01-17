package com.example.composevkclient.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults.colors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composevkclient.MainActivityViewModel
import com.example.composevkclient.domain.StatisticsType

@Composable
fun MainScreen(viewModel: MainActivityViewModel) {
    Log.d("RECOMPOSITION", "MainScreen")
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .border(BorderStroke(0.3.dp, Color.LightGray)),
                containerColor = MaterialTheme.colorScheme.primary,
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                val selectedItemPosition = remember {
                    mutableIntStateOf(0)
                }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourites,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPosition.intValue == index,
                        onClick = { selectedItemPosition.intValue = index },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        colors = colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        val feedPosts = viewModel.models
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = feedPosts.value, key = { it.id }) { feedPost ->
                val dismissState = rememberSwipeToDismissBoxState(
                    positionalThreshold = { it / 2 }
                )
                if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                    viewModel.delete(feedPost)
                }
                SwipeToDismissBox(
                    modifier = Modifier.animateItem(),
                    enableDismissFromStartToEnd = false,
                    state = dismissState,
                    backgroundContent = {
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize()
                                .background(Color.Red.copy(alpha = 0.5f)),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = "Delete item",
                                color = Color.White,
                                fontSize = 24.sp
                            )
                        }
                    }
                ) {
                    PostCard(
                        feedPost = feedPost,
                        onViewsClickListener = {
                            viewModel.increaseCount(feedPost, StatisticsType.VIEWS)
                        },
                        onShareClickListener = {
                            viewModel.increaseCount(feedPost, StatisticsType.SHARES)
                        },
                        onCommentClickListener = {
                            viewModel.increaseCount(feedPost, StatisticsType.COMMENTS)
                        },
                        onLikeClickListener = {
                            viewModel.increaseCount(feedPost, StatisticsType.LIKES)
                        },

                        )
                }
            }
        }
    }
}