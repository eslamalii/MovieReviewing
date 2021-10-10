package com.example.movieapp.recyclerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.model.series.tvShows.Results
import com.example.movieapp.model.series.tvShows.TvShows
import com.example.movieapp.util.Genre

class TrendingRecyclerView(private val cells: TvShows, private var context: Context) :
    RecyclerView.Adapter<TrendingRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.detials_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell: Results = cells.results[position]
        val s = Genre.getGenre(cell.genreIds)
        holder.genre.text = s.split(",")[0]
        holder.rate.text = cell.voteAverage.toString()

        Glide
            .with(context)
            .load(cell.getImageUrl())
            .fitCenter()
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return cells.results.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.posterHol)
        val genre: TextView = view.findViewById(R.id.genreHol)
        var rate: TextView = view.findViewById(R.id.rateHol)
    }
}