/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AffirmationsTheme {

                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //AffirmationsApp()
                    SearchableAffirmationsApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(
            start = WindowInsets
                .safeDrawing
                .asPaddingValues()
                .calculateStartPadding(layoutDirection),
            end = WindowInsets
                .safeDrawing
                .asPaddingValues()
                .calculateEndPadding(layoutDirection),
        )) {
        AffirmationList(
            affirmationList = Datasource().loadAffirmations()
        )
    }
}


@Composable
fun AffirmationsCard(
    affirmation: Affirmation,
    modifier: Modifier= Modifier,
) {
    var isClick by remember {
        mutableStateOf(false)

    }
    var mediaPlayer: MediaPlayer? by remember {
        mutableStateOf(null)
    }
    var context = LocalContext.current;

    Card(modifier=modifier) {


        Column {
            Image(painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier=Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            IconButton(
                onClick = {
                    isClick = !isClick
                if (isClick) {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(context,affirmation.soundRes)
                    mediaPlayer?.start()
                } else {
                    mediaPlayer?.pause()
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
            } ) {

                Icon(painter = painterResource(
                       id =
                       if (isClick) affirmation.iconPause else affirmation.iconResourceId) ,
                       contentDescription ="" ,
                    modifier =Modifier.height(70.dp),)


            }

        }

    }
}
@Composable
fun AffirmationList(affirmationList: List<Affirmation>,modifier: Modifier=Modifier){
    LazyColumn(modifier=modifier) {
        items(affirmationList){
            affirmation ->
            AffirmationsCard(
                affirmation = affirmation,

                modifier = modifier.padding(10.dp),)
        }
    }
}


@SuppressLint("ResourceType")
@Preview
@Composable
private fun AffirmationCardPreview(){
    AffirmationsCard(Affirmation(R.string.affirmation1,R.drawable.image1,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.song))
}


@Preview
@Composable
fun SearchableAffirmationsApp() {
    var searchText by remember { mutableStateOf("") }
    val affirmationList = Datasource().loadAffirmations()
    val filteredList = affirmationList.filter {
        LocalContext.current.getString(it.stringResourceId).contains(searchText, ignoreCase = true)
    }
    Column {
        SearchViewComponent(onQueryChanged = { query ->
            searchText = query
        })
        AffirmationList(affirmationList = filteredList)
    }
}

@Composable
fun SearchViewComponent(onQueryChanged: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            SearchView(context).apply {
                isIconifiedByDefault = false
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            onQueryChanged(it)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let {
                            onQueryChanged(it)
                        }
                        return true
                    }
                })
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}