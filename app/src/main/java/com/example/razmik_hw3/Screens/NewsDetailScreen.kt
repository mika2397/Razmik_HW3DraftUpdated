package com.example.razmik_hw3.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.razmik_hw3.newsResources.News
import androidx.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.razmik_hw3.R

@Composable
fun NewsDetailScreen(navController: NavController,
                     news: News,
    //   newsListResult: List<News>,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        //title of the news Article
        Text(text = news.title,
            style = TextStyle(fontSize = 24.sp),
            textAlign =  TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(12.dp))

        //author of the article
        Text(text = news.author,
            style = TextStyle(fontSize = 12.sp),
            textAlign =  TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth())


        //Image from the article
        AsyncImage(
            model = news.imageUrl,
            error = painterResource(id = R.drawable.errorimg),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            contentDescription = "news article image expanded",
            placeholder = painterResource(id = R.drawable.loadingimg)
        )

        //Source of the article
        Text(text = news.name,
            style = TextStyle(fontSize = 12.sp),
            textAlign =  TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth())

        //Description of the Article
        Text(text = news.description,
            style = TextStyle(fontSize = 20.sp),
            textAlign =  TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 8.dp))

    }
}