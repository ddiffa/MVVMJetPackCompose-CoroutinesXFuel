package com.hellodiffa.jetpackcomposeexample.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beust.klaxon.KlaxonException
import com.github.kittinunf.fuel.core.HttpException
import com.hellodiffa.jetpackcomposeexample.common.ViewState
import com.hellodiffa.jetpackcomposeexample.data.model.Article
import com.hellodiffa.jetpackcomposeexample.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val repository: NewsRepositoryImpl
) : ViewModel() {

    private val _news = MutableLiveData<ViewState>()
    val news: LiveData<ViewState> get() = _news

    private fun setResource(resource: ViewState) {
        _news.postValue(resource)
    }

    internal fun getAllArticle(context: Context) {

        runBlocking {
            try {
                setResource(ViewState.Loading)
                delay(2_000)
                val (data, error) = repository.getAllArticle()
                if (data != null) {
                    setResource(ViewState.Success(data.listOfArticle.map {
                        Article(
                            name = it.title,
                            description = it.description,
                            image = it.urlToImage
                        )
                    }))
                } else {
                    setResource(ViewState.Error(error?.message ?: "Error occured", context))
                }
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> setResource(
                        ViewState.Error(
                            "A network request exception was thrown: ${e.message}",
                            context
                        )
                    )
                    is KlaxonException -> setResource(
                        ViewState.Error(
                            "A serialization/deserialization exception was thrown: ${e.message}",
                            context
                        )
                    )
                    else -> setResource(
                        ViewState.Error(
                            "An exception [${e.javaClass.simpleName}\"] was thrown",
                            context
                        )
                    )
                }
            }
        }
    }

}