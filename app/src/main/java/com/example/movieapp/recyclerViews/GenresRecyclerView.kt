package com.example.movieapp.recyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.series.genres.Genres
import com.example.movieapp.model.series.genres.GenresValues

class GenresRecyclerView(
    private val genresList: Genres?
) :
    RecyclerView.Adapter<GenresRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.genres_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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