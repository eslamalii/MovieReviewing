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
import com.example.movieapp.util.GenreSeries

class TrendingRecyclerView(
    private val cells: TvShows,
    private var context: Context,
    private val listener: OnItemCLickListener
) :
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
        val s = GenreSeries.getGenre(cell.genreIds)
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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var image: ImageView = view.findViewById(R.id.posterHol)
        val genre: TextView = view.findViewById(R.id.genreHol)
        var rate: TextView = view.findViewById(R.id.rateHol)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClicked(cells.results[position])
            }
        }


    }

    interface OnItemCLickListener {
        fun onItemClicked(position: Results)
    }
}