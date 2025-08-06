package eu.tutorials.musicappui

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon: Int, val name: String)
    val libraries = listOf(
        Lib(R.drawable.outline_music_cast_24,"Playlist"),
        Lib(R.drawable.outline_artist_24, "Artist"),
        Lib(R.drawable.album_6_24,"Album"),
        Lib(R.drawable.outline_music_note_24,"Songs")
    )

