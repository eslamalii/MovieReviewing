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
import com.example.movieapp.model.movies.popularMovies.PopularMovies
import com.example.movieapp.model.movies.popularMovies.Results
import com.example.movieapp.util.GenreMovies

class TrendingRecyclerViewM(
    private val cells: PopularMovies,
    private var context: Context,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<TrendingRecyclerViewM.ViewHolder>() {

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
        val s = GenreMovies.getGenre(cell.genreIds)
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
            val p = adapterPosition
            if (p != RecyclerView.NO_POSITION) {
                listener.onItemClicked(cells.results[p].id)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }
}
