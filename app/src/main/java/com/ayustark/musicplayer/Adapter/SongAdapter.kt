package com.ayustark.musicplayer.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ayustark.musicplayer.Model.SongInfo
import com.ayustark.musicplayer.databinding.SongItemBinding

class SongAdapter(private val context: Context, private val songs: ArrayList<SongInfo>) :
    BaseAdapter() {
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

        }
        return binding.root
    }
}