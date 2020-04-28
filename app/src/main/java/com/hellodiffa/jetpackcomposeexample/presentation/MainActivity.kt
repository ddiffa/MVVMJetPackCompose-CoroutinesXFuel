package com.hellodiffa.jetpackcomposeexample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setContent {
            MaterialTheme {
                showData(viewModel = viewModel)
            }
        }
    }

    private fun setupViewModel() {
        viewModel.getAllArticle(context = applicationContext)
    }


}

@Composable
fun showData(viewModel: MainViewModel) {
    Column {
        TopAppBar(title = { Text(text = "News API") })
        val data = observe(data = viewModel.news)
        data?.buildUI()
    }
}

@Composable
fun <T> observe(data: LiveData<T>): T? {
    var result by state { data.value }
    val observer = remember { Observer<T> { result = it } }

    onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    return result
}
