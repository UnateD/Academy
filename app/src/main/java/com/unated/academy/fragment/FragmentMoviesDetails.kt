package com.unated.academy.fragment

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
import com.bumptech.glide.Glide
import com.unated.academy.R
import com.unated.academy.adapter.ActorsAdapter
import com.unated.academy.data.Movie
import com.unated.academy.domain.DataSource
import com.unated.academy.interfaces.NavigationListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesDetails : BaseFragment() {

    private var tvAge: TextView? = null
    private var tvTitle: TextView? = null
    private var tvGenre: TextView? = null
    private var tvReviews: TextView? = null
    private var rbRating: RatingBar? = null
    private var tvStoryline: TextView? = null
    private var rvActors: RecyclerView? = null
    private var ivHeader: ImageView? = null

    private var navigationListener: NavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) {
            navigationListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationListener = null
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

        tvAge = view.findViewById(R.id.tv_age)
        tvTitle = view.findViewById(R.id.tv_title)
        tvGenre = view.findViewById(R.id.tv_genre)
        tvReviews = view.findViewById(R.id.tv_reviews)
        rbRating = view.findViewById(R.id.rb_rating)
        tvStoryline = view.findViewById(R.id.tv_storyline)
        rvActors = view.findViewById(R.id.rv_actors)
        ivHeader = view.findViewById(R.id.iv_header)

        view.findViewById<LinearLayout>(R.id.llBack)
            .setOnClickListener { navigationListener?.goToMain() }

        CoroutineScope(Dispatchers.IO).launch { getMovie(arguments?.getInt(EXTRA_MOVIE_ID, 0)) }
    }

    private suspend fun getMovie(movieId: Int?) = withContext(Dispatchers.IO) {
        movieId?.let { dataProvider?.dataSource()?.getMovieById(movieId)?.let { fillViews(it) } }
    }

    private suspend fun fillViews(movie: Movie) = withContext(Dispatchers.Main) {
        val adapter = ActorsAdapter(movie.actors)
        rvActors?.adapter = adapter
        tvAge?.text = movie.minimumAge.toString()
        tvTitle?.text = movie.title
        tvGenre?.text = movie.genres.joinToString(separator = ", ") { it.name }
        tvReviews?.text =
            context?.getString(R.string.title_movie_reviews_count, movie.numberOfRatings)
        rbRating?.rating = movie.ratings / 2
        tvStoryline?.text = movie.overview
        Glide.with(ivHeader!!).load(movie.poster).into(ivHeader!!)
    }

    companion object {

        private const val EXTRA_MOVIE_ID = "extra_movie_id"

        fun newInstance(id: Int): FragmentMoviesDetails {
            val bundle = Bundle()
            bundle.putInt(EXTRA_MOVIE_ID, id)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = bundle
            return fragment
        }
    }
}