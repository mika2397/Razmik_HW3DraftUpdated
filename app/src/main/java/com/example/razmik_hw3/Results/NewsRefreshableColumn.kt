package com.example.razmik_hw3.Results

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.razmik_hw3.Screens.NewsRow
import com.example.razmik_hw3.Screens.NewsScreen
import com.example.razmik_hw3.Screens.ScreensData
import com.example.razmik_hw3.newsResources.News
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsRefreshableColumn(
    data: List<News>,
    onRefresh: () -> Unit,
    navController: NavController,
    onSelectFilter:(String) -> Unit,
    onSearch:(String) -> Unit
) {
    val currentState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = currentState,
        onRefresh = {
            onRefresh()
            currentState.isRefreshing = false
        }) {


        LazyColumn {
            itemsIndexed(data) { index, news ->
                NewsRow(news = news) {
                    navController.navigate(ScreensData.DetailScreen.routeToPage + "/$index")
                }
            }
        }
    }
}

