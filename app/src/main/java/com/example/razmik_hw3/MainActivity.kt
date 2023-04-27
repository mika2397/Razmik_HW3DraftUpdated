package com.example.razmik_hw3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.razmik_hw3.DataClasses.NewsData
import com.example.razmik_hw3.UI.SearchBar
import com.example.razmik_hw3.newsResources.News
import com.example.razmik_hw3.UI.FilterExpand
import android.util.Log
import androidx.compose.foundation.background
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.razmik_hw3.DataClasses.Filter
import com.example.razmik_hw3.Screens.NewsDetailScreen
import com.example.razmik_hw3.Screens.NewsScreen
import com.example.razmik_hw3.Screens.ScreensData

class MainActivity : AppCompatActivity() {
    private val dataLoaderViewModel: DataLoaderViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        var categoryText = ""
        var searchText = ""
        super.onCreate(savedInstanceState)
        dataLoaderViewModel.loadNews()
        dataLoaderViewModel.newsList.observe(this) { newsList, ->



            setContent {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ScreensData.MainScreen.routeToPage
                ) {

                    composable(
                        route = ScreensData.MainScreen.routeToPage) {
                        NewsScreen(
                            navController = navController,
                            newsListResult = newsList,
                            onRefresh = dataLoaderViewModel::loadNews,
                            onSelectFilter = {
                                categoryText = it
                                dataLoaderViewModel.loadFilteredNews(it, searchText)
                            },
                            onSearch = {
                                searchText = it
                                dataLoaderViewModel.loadFilteredNews(categoryText,it)
                            }
                            //onClick = dataLoaderViewModel::loadNews,
                        )
                    }

                    composable(
                        route = ScreensData.DetailScreen.routeToPage + "/{id}",
                        arguments = listOf(navArgument(name = "id") {
                            type = NavType.IntType
                        }
                        )) {
                            dataVal ->
                        val id = dataVal.arguments?.getInt("id")

                        id?.let {
                            newsList[it]
                        }?.let {
                            NewsDetailScreen(navController, news = it)
                        }

                    }
                }
            }
        }
    }
}


//@Composable
//fun NewsList(newsList: List<News>) {
//
//
//    LazyColumn {
//        item {
//
//            Column(
//                Modifier
//                    .fillMaxWidth()
//                    .background(Color.LightGray)){
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(12.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                ) {
//                    SearchBar(
//                        onSearch = { //query ->
//
//                    })
//                    FilterExpand(
//
//                        filters = listOf(
//                            Filter(1, "Business"),
//                            Filter(2, "Entertainment"),
//                            Filter(3, "General"),
//                            Filter(4, "Health"),
//                            ),
//
//                        onFilterSelected = { filter ->
//                            Log.d("Filter done", "filter")
//                      //  onFilterSelected = { filter ->
//                            //onSelectFilter(filter.category)
//
//                    }
//                )
//
//                }
//
//            }
//        }
//        items(newsList) {   news ->
//            NewsCard(news)
//        }
//    }
//}
//
//@Composable
//fun NewsCard(news: News) {
//
//    Column {
//
//        Text(text = news.title,
//            style = TextStyle(fontSize = 24.sp),
//            textAlign =  TextAlign.Center,
//            modifier = Modifier
//                .wrapContentHeight()
//                .fillMaxWidth())
//
//
//        AsyncImage(
//
//            model = news.imageUrl,
//            error = painterResource(id = R.drawable.errorimg),
//            modifier = Modifier
//                .wrapContentHeight()
//                .fillMaxWidth(),
//            contentDescription = "",
//            placeholder = painterResource(id = R.drawable.loadingimg)
//        )
//        Text(text = news.author,
//            style = TextStyle(fontSize = 12.sp),
//            textAlign =  TextAlign.Center,
//            modifier = Modifier
//                .wrapContentHeight()
//                .fillMaxWidth())
//
//        Divider(
//            color = Color.Black,
//            modifier = Modifier.padding(vertical = 20.dp)
//        )
//    }
//
//}