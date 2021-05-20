package com.ayustark.musicplayer.Activity

import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.ayustark.musicplayer.Adapter.SongAdapter
import com.ayustark.musicplayer.Model.SongInfo
import com.ayustark.musicplayer.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val songList = arrayListOf<SongInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GlobalScope
//        loadSongsOnline()
        loadLocalSongs()
        val adapter = SongAdapter(this@MainActivity, binding, songList)
        binding.songList.adapter = adapter
    }

    private fun loadSongsOnline() {
        songList.add(SongInfo("001", "Arijit", "https://server6.mp3quran.net/thubti/001.mp3"))
        songList.add(SongInfo("002", "Sonu", "https://server6.mp3quran.net/thubti/002.mp3"))
        songList.add(SongInfo("003", "Ayush", "https://server6.mp3quran.net/thubti/003.mp3"))
        songList.add(SongInfo("004", "Yushavi", "https://server6.mp3quran.net/thubti/004.mp3"))
        songList.add(SongInfo("005", "Ishita", "https://server6.mp3quran.net/thubti/005.mp3"))
    }

    private fun loadLocalSongs() {
        val songsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val cursor = contentResolver.query(songsUri, null, selection, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            do {
                val songUrl = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                val songAuthor =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                val songName =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                songList.add(SongInfo(songName, songAuthor, songUrl))
            } while (cursor.moveToNext())
        }
        cursor?.close()

    }
}