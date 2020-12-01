package com.unated.academy.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.unated.academy.R
import com.unated.academy.adapter.ActorsAdapter
import com.unated.academy.domain.DataSource

class FragmentMoviesDetails : Fragment() {

    private lateinit var adapter: ActorsAdapter
    private var dataSource: DataSource = DataSource()

    private var rvActors: RecyclerView? = null
    private var tvAge: TextView? = null
    private var tvTitle: TextView? = null
    private var tvGenre: TextView? = null
    private var tvReviews: TextView? = null
    private var rbRating: RatingBar? = null
    private var tvStoryline: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.llBack)
            .setOnClickListener { activity?.onBackPressed() }

        initViews()
        fillViews(arguments?.get(EXTRA_MOVIE_ID) as Int)
    }

    private fun initViews() {
        tvAge = view?.findViewById(R.id.tv_age)
        tvTitle = view?.findViewById(R.id.tv_title)
        tvGenre = view?.findViewById(R.id.tv_genre)
        tvReviews = view?.findViewById(R.id.tv_reviews)
        rbRating = view?.findViewById(R.id.rb_rating)
        tvStoryline = view?.findViewById(R.id.tv_storyline)
        rvActors = view?.findViewById(R.id.rv_actors)
    }

    private fun fillViews(movieId: Int) {
        dataSource.getMovieById(movieId)?.let { movie ->
            adapter = ActorsAdapter(movie.actors)
            rvActors?.adapter = adapter
            tvAge?.text = movie.ageRating
            tvTitle?.text = movie.title
            tvGenre?.text = movie.genre
            tvReviews?.text =
                context?.getString(R.string.title_movie_reviews_count, movie.reviewsCount)
            rbRating?.rating = movie.rating
            tvStoryline?.text = movie.storyline
        }
    }

    companion object {

        private const val EXTRA_MOVIE_ID = "extra_movie_id"

        fun newInstance(id: Int): FragmentMoviesDetails {
            var bundle = Bundle()
            bundle.putInt(EXTRA_MOVIE_ID, id)
            var fragment = FragmentMoviesDetails()
            fragment.arguments = bundle
            return fragment
        }
    }
}