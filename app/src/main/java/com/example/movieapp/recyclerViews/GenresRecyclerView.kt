package com.example.movieapp.recyclerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.model.series.genres.GenresValues

class GenresRecyclerView(
    private val genresList: Genres?,
    private val context: Context
) :
    RecyclerView.Adapter<GenresRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenresRecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.genres_row, parent, false))
    }

    override fun onBindViewHolder(holder: GenresRecyclerView.ViewHolder, position: Int) {
        val list: GenresValues = genresList!!.genres[position]
        holder.item.text = list.name
    }

    override fun getItemCount(): Int {
        return genresList!!.genres.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var item: TextView = view.findViewById(R.id.genresTextTab)
    }


}