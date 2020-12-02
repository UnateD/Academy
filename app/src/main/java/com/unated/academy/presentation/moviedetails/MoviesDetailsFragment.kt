package com.unated.academy.presentation.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.unated.academy.R
import com.unated.academy.domain.provideDataSource
import com.unated.academy.presentation.NavigationListener

class MoviesDetailsFragment : Fragment() {

    private var navigationListener: NavigationListener? = null
    private val dataSource = provideDataSource()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is NavigationListener){
            navigationListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.llBack).setOnClickListener {
            navigationListener?.goToMain()
        }

        (arguments?.get(EXTRA_MOVIE_ID) as? Int)?.let { fillViews(it) } ?: fillViewsByDefault()
    }

    override fun onDetach() {
        super.onDetach()

        navigationListener = null
    }

    private fun fillViewsByDefault() {
        dataSource.getMovies().firstOrNull()?.let { fillViews(it.id) }
    }

    private fun fillViews(movieId: Int) {
        val tvAge: TextView? = view?.findViewById(R.id.tv_age)
        val tvTitle: TextView? = view?.findViewById(R.id.tv_title)
        val tvGenre: TextView? = view?.findViewById(R.id.tv_genre)
        val tvReviews: TextView? = view?.findViewById(R.id.tv_reviews)
        val rbRating: RatingBar? = view?.findViewById(R.id.rb_rating)
        val tvStoryline: TextView? = view?.findViewById(R.id.tv_storyline)
        val rvActors: RecyclerView? = view?.findViewById(R.id.rv_actors)
        val ivHeader: ImageView? = view?.findViewById(R.id.iv_header)

        dataSource.getMovieById(movieId)?.let { movie ->
            rvActors?.adapter = ActorsAdapter(movie.actors)
            tvAge?.text = movie.ageRating
            tvTitle?.text = movie.title
            tvGenre?.text = movie.genre
            tvReviews?.text = context?.getString(R.string.title_movie_reviews_count, movie.reviewsCount)
            rbRating?.rating = movie.rating
            tvStoryline?.text = movie.storyline
            ivHeader?.setImageResource(movie.fullCoverImg)
        }
    }

    companion object {
        private const val EXTRA_MOVIE_ID = "extra_movie_id"

        fun newInstance(id: Int): MoviesDetailsFragment {
            val bundle = Bundle()
            bundle.putInt(EXTRA_MOVIE_ID, id)
            val fragment = MoviesDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}