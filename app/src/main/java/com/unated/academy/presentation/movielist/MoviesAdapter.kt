package com.unated.academy.presentation.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.unated.academy.R
import com.unated.academy.data.Configuration
import com.unated.academy.data.Movie
import com.unated.academy.presentation.movielist.MovieClickListener

class MoviesAdapter(var configuration: Configuration, var listener: MovieClickListener) :
    PagedListAdapter<Movie, MoviesAdapter.BaseViewHolder>(MovieDiffUtilCallback) {

    companion object {
        val MovieDiffUtilCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            getItem(position)?.let { movie ->
                holder.bind(movie)
                holder.itemView.setOnClickListener { listener.onMovieClicked(movie.id) }
            }
        }
    }

    inner class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {

        private val ivCover: ImageView = itemView.findViewById(R.id.iv_cover)
        private val ivFavorite: ImageView = itemView.findViewById(R.id.iv_favorite)
        private val tvAge: TextView = itemView.findViewById(R.id.tv_age)
        private val tvGenre: TextView = itemView.findViewById(R.id.tv_genre)
        private val tvReviews: TextView = itemView.findViewById(R.id.tv_reviews)
        private val rbRating: RatingBar = itemView.findViewById(R.id.rb_rating)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvDuration: TextView = itemView.findViewById(R.id.tv_duration)

        fun bind(data: Movie) {
            Glide.with(ivCover)
                .load("${configuration.images.base_url}/${configuration.images.poster_sizes.last()}/${data.poster}")
                .apply(
                    RequestOptions().transform(
                        CenterCrop(),
                        RoundedCorners(16)
                    )
                ).into(ivCover)
            ivFavorite.setImageResource(
                when (data.isFavorite) {
                    true -> R.drawable.ic_fav_selected
                    false -> R.drawable.ic_fav_not_selected
                }
            )
            tvAge.text = if (data.adult) "16" else "13"
            tvGenre.text = data.displayGenres.joinToString(separator = ", ") { it.name }
            tvReviews.text =
                context.getString(R.string.title_movie_reviews_count, data.numberOfRatings)
            rbRating.rating = data.ratings / 2
            tvTitle.text = data.title
            tvDuration.text = context.getString(R.string.title_movie_duration, data.runtime)
        }
    }

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context