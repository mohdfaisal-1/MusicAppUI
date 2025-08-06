package eu.tutorials.musicappui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BrowseScreen(){
    val categories = listOf("Enjoy", "Excitment", "Gym", "Walk", "Running", "Exercise")

    LazyVerticalGrid(
        GridCells.Fixed(2)
    ) {
        items(categories){
            cat ->
            BrowsItem(cat = cat, drawable = R.drawable.outline_music_note_add_24)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun browse(){
    BrowseScreen()
}