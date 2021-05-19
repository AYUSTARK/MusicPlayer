package com.ayustark.musicplayer.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayustark.musicplayer.Adapter.SongAdapter
import com.ayustark.musicplayer.Model.SongInfo
import com.ayustark.musicplayer.R
import com.ayustark.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val songList = arrayListOf<SongInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadSongs()
        val adapter = SongAdapter(this@MainActivity,songList)
        binding.songList.adapter=adapter
    }

    private fun loadSongs(){
        songList.add(SongInfo("001","Arijit","http://server6.mp3quran.net/thubti/001.mp3"))
        songList.add(SongInfo("002","Sonu","http://server6.mp3quran.net/thubti/001.mp3"))
        songList.add(SongInfo("003","Ayush","http://server6.mp3quran.net/thubti/001.mp3"))
        songList.add(SongInfo("004","Yushavi","http://server6.mp3quran.net/thubti/001.mp3"))
        songList.add(SongInfo("005","Ishita","http://server6.mp3quran.net/thubti/001.mp3"))
    }
}