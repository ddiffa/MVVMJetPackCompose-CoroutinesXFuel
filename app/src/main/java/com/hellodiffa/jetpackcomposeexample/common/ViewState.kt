package com.hellodiffa.jetpackcomposeexample.common

import android.content.Context
import android.widget.Toast
import androidx.compose.Composable
import com.hellodiffa.jetpackcomposeexample.data.model.Article
import com.hellodiffa.jetpackcomposeexample.presentation.ui.LoadingUI
import com.hellodiffa.jetpackcomposeexample.presentation.ui.NewsUI

sealed class ViewState {
    @Composable
    abstract fun buildUI()

    object Loading : ViewState() {
        @Composable
        override fun buildUI() {
            LoadingUI.LiveDataLoading()
        }
    }

    class Error(private val reason: String, private val context: Context) :
        ViewState() {
        @Composable
        override fun buildUI() {
            Toast.makeText(context, reason, Toast.LENGTH_SHORT).show()
        }
    }

    class Success(private val data: List<Article>) : ViewState() {
        @Composable
        override fun buildUI() {
            NewsUI.LiveDataList(
                articles = data
            )
        }
    }
}