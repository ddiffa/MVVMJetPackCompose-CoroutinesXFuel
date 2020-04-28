package com.hellodiffa.jetpackcomposeexample.presentation.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment.Companion.CenterHorizontally
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.wrapContentWidth
import androidx.ui.material.CircularProgressIndicator


object LoadingUI {

    @Composable
    fun LiveDataLoading() {
        Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
            CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
        }
    }
}
