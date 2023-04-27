package com.example.razmik_hw3.Screens

sealed class ScreensData(val routeToPage: String) {
    object MainScreen : ScreensData("main_screen")
    object DetailScreen : ScreensData("detail_screen")
}
