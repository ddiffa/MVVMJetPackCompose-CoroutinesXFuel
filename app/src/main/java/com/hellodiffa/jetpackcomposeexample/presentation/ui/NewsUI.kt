package com.hellodiffa.jetpackcomposeexample.presentation.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.*
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.material.ListItem
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.hellodiffa.jetpackcomposeexample.data.model.Article
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


object NewsUI {
    @Composable
    fun LiveDataList(articles: List<Article>) {
        Column {
            AdapterList(data = articles, modifier = Modifier.fillMaxHeight()) { article ->
                Card(
                    shape = RoundedCornerShape(4.dp), color = Color.White,
                    modifier = Modifier.fillMaxWidth() + Modifier.padding(8.dp),
                    elevation = 2.dp
                ) {
                    ListItem(
                        text = {
                            article.name?.let {
                                Text(
                                    text = it,
                                    style = TextStyle(
                                        fontFamily = FontFamily.Serif,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }, secondaryText = {
                            article.description?.let {
                                Text(
                                    text = it,
                                    style = TextStyle(
                                        fontFamily = FontFamily.Serif,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.DarkGray
                                    )
                                )
                            }
                        }, icon = {
                            article.image?.let {
                                NetworkImageComponentPicasso(
                                    url = it,
                                    modifier = Modifier.preferredWidth(60.dp) + Modifier.preferredHeight
                                        (60.dp)
                                )
                            }
                        }
                        , modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun NetworkImageComponentPicasso(url: String,
                                     modifier: Modifier = Modifier.fillMaxWidth() +
                                             Modifier.preferredHeightIn(maxHeight = 200.dp)) {
        var image by state<ImageAsset?> { null }
        var drawable by state<Drawable?> { null }
        onCommit(url) {
            val picasso = Picasso.get()
            val target = object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    drawable = placeHolderDrawable
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    drawable = errorDrawable
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    image = bitmap?.asImageAsset()
                }
            }
            picasso
                .load(url)
                .into(target)

            onDispose {
                image = null
                drawable = null
                picasso.cancelRequest(target)
            }
        }

        val theImage = image
        val theDrawable = drawable
        if (theImage != null) {
            Box(modifier = modifier,
                gravity = ContentGravity.Center
            ) {
                Image(asset = theImage)
            }
        } else if (theDrawable != null) {
            Canvas(modifier = modifier) {
                theDrawable.draw(this.nativeCanvas)
            }
        }
    }

}

