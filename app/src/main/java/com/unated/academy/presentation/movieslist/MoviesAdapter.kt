package com.unated.academy.presentation.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unated.academy.R
import com.unated.academy.data.model.Movie
import com.unated.academy.presentation.BaseViewHolder
import com.unated.academy.presentation.context

class MoviesAdapter(
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    private var movies = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            movies[position].let { movie ->
                holder.itemView.setOnClickListener { movieClickListener.onClick(movie.id) }
                holder.bind(movie)
            }
        }
    }

    fun setMovies(movies: ArrayList<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val ivCover: ImageView = itemView.findViewById(R.id.iv_cover)
        private val ivFavorite: ImageView = itemView.findViewById(R.id.iv_favorite)
        private val tvAge: TextView = itemView.findViewById(R.id.tv_age)
        private val tvGenre: TextView = itemView.findViewById(R.id.tv_genre)
        private val tvReviews: TextView = itemView.findViewById(R.id.tv_reviews)
        private val rbRating: RatingBar = itemView.findViewById(R.id.rb_rating)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvDuration: TextView = itemView.findViewById(R.id.tv_duration)

        fun bind(data: Movie) {
            ivCover.setImageResource(data.smallCoverImg)
            ivFavorite.setImageResource(
                when (data.isFavorite) {
                    true -> R.drawable.ic_fav_selected
                    false -> R.drawable.ic_fav_not_selected
                }
            )
            tvAge.text = data.ageRating
            tvGenre.text = data.genre
            tvReviews.text = context.getString(
                R.string.title_movie_reviews_count,
                data.reviewsCount
            )
            rbRating.rating = data.rating
            tvTitle.text = data.title
            tvDuration.text = context.getString(R.string.title_movie_duration, data.duration)
        }
    }
}

interface MovieClickListener {
    fun onClick(movieId: Int)
}