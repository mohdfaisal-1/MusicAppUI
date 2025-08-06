package eu.tutorials.musicappui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Hoem(){
    val categories = listOf("Fun", "Relax", "Happy", "Motivation", "Workout", "Party")

    val grouped = listOf<String>("Hits", "Top Rated", "Most Liked", "Favourits", "Road Trip")

    LazyColumn {
        grouped.forEach {
            stickyHeader {
                Text(text = it, modifier = Modifier.padding(16.dp))

                LazyRow {
                    items(categories){
                        cat ->
                        BrowsItem(cat = cat, drawable = R.drawable.outline_music_cast_24)
                    }
                }
            }
        }
    }
}
@Composable
fun BrowsItem(cat: String, drawable: Int){
    Card (
        modifier = Modifier.padding(16.dp).size(150.dp),
        border = BorderStroke(2.dp, color = Color.Black)
    ){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = cat)
            Image(painter = painterResource(id = drawable), contentDescription = cat)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun home(){
    Hoem()
}



















