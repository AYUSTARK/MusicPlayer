package com.ayustark.musicplayer.Adapter

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.ayustark.musicplayer.Model.SongInfo
import com.ayustark.musicplayer.databinding.ActivityMainBinding
import com.ayustark.musicplayer.databinding.SongItemBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SongAdapter(
    private val context: Context,
    private val main: ActivityMainBinding,
    private val songs: ArrayList<SongInfo>
) :
    BaseAdapter() {
    var mp: MediaPlayer = MediaPlayer()
    override fun getCount(): Int {
        return songs.size
    }

    override fun getItem(i: Int): Any {
        return songs[i]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(i: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = SongItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        val song = songs[i]
        binding.songName.text = song.title
        binding.songAuthor.text = song.authorName
        binding.btnPlay.setOnClickListener {
            try {
//                mp = MediaPlayer()//.create(context, Uri.parse(song.songUrl))
                Log.d("Media", song.songUrl)
                if (mp.isPlaying) {
                    mp.pause()
                    val play = "Play"
                    binding.btnPlay.text = play
                } else {
                    mp = MediaPlayer()
                    mp.setDataSource(context, Uri.parse(song.songUrl))
                    mp.prepare()
                    mp.start()
                    main.seekBar.max = mp.duration
                    SongTrack().run()
                    song.isPlaying = true
                    val stop = "Stop"
                    binding.btnPlay.text = stop
                }
            } catch (ex: Exception) {
                Toast.makeText(context, "Error in Player", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "$ex", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    inner class SongTrack {

        fun run() {
            GlobalScope.launch {
                while (true) {
                    try {
                        delay(1000)
                    } catch (ex: Exception) {
                    }
                    (context as Activity).runOnUiThread {
                        main.seekBar.setProgress(mp.currentPosition, true)
                    }
                }
            }
        }
    }
}