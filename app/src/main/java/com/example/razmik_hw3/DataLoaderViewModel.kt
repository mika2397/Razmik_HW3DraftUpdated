package com.example.razmik_hw3

import androidx.lifecycle.*

import com.example.razmik_hw3.DataClasses.NewsData
import com.example.razmik_hw3.Results.NewsRepo
import com.example.razmik_hw3.newsResources.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataLoaderViewModel : ViewModel(){

    private val refreshTrigger = MutableLiveData<Boolean>()

    private val newsListBase: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>> = newsListBase

    fun loadNews() {
            viewModelScope.launch {
                val resultValues = NewsRepo().injectNews()
                newsListBase.postValue(resultValues)

        }
    }

    fun loadFilteredNews(category:String,searchData:String) {
        viewModelScope.launch {
            val resultValues = NewsRepo().injectNews(category,searchData)
            newsListBase.postValue(resultValues)
        }
    }

}