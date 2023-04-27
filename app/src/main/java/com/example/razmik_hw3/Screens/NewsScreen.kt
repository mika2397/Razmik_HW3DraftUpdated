package com.example.razmik_hw3.Screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.razmik_hw3.DataClasses.Filter
import com.example.razmik_hw3.newsResources.News
import com.example.razmik_hw3.DataClasses.NewsData
import com.example.razmik_hw3.R
import com.example.razmik_hw3.Results.NewsRefreshableColumn
import com.example.razmik_hw3.UI.SearchBar
import com.example.razmik_hw3.UI.FilterExpand
import com.example.razmik_hw3.Results.NewsRepo

@Composable
fun NewsScreen(navController: NavController,
               newsListResult: List<News>,
               onRefresh: () -> Unit,
               onSelectFilter:(String) -> Unit,
               onSearch:(String) -> Unit
) {


//    if(newsListResult.isEmpty()) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center) {
//
//            Text(text = "No results found",
//                textAlign = TextAlign.Center,
//                style = TextStyle(fontSize = 18.sp, color = Color.DarkGray))
//        }
//    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SearchBar(onSearch = { searchValue ->
                onSearch(searchValue)
            },
            )

            FilterExpand(
                filters =  listOf(
                    Filter(1, "Business"),
                    Filter(2, "Entertainment"),
                    Filter(3, "General"),
                    Filter(4, "Health"),
                ),
                onFilterSelected = {filter ->
                    onSelectFilter(filter.category)
                })
        }


        NewsRefreshableColumn(
            data = newsListResult,
            onRefresh = onRefresh,
            navController = navController,
            onSelectFilter = onSelectFilter,
            onSearch = onSearch
        )
    }
}

@Composable
fun NewsRow(news: News, onClick: () -> Unit) {
    Card(
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick),
    )
    {
        Column (
            modifier = Modifier
                .background(Color.LightGray)){
            Text(
                text = news.title,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(height = 8.dp))
            AsyncImage(

                model = news.imageUrl,
                error = painterResource(id = R.drawable.errorimg),
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                contentDescription = "news article image",
                placeholder = painterResource(id = R.drawable.loadingimg)
            )
            Spacer(modifier = Modifier.height(height = 8.dp))
            Text(
                text = news.author,
                style = TextStyle(fontSize = 12.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
        }
    }
}