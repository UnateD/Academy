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
import com.unated.academy.domain.DataSource
import com.unated.academy.interfaces.NavigationListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentMoviesDetails : BaseFragment() {

    private var navigationListener: NavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is NavigationListener){
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
        view.findViewById<LinearLayout>(R.id.llBack)
            .setOnClickListener { navigationListener?.goToMain() }

        fillViews(arguments?.get(EXTRA_MOVIE_ID) as Int)
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

        CoroutineScope(Dispatchers.IO).launch {
            dataProvider?.dataSource()?.getMovieById(movieId)?.let { movie ->
                CoroutineScope(Dispatchers.Main).launch {
                    val adapter = ActorsAdapter(movie.actors)
                    rvActors?.adapter = adapter
                    tvAge?.text = movie.minimumAge.toString()
                    tvTitle?.text = movie.title
                    tvGenre?.text = movie.genres.joinToString(separator = ", ") { it.name }
                    tvReviews?.text =
                        context?.getString(R.string.title_movie_reviews_count, movie.numberOfRatings)
                    rbRating?.rating = movie.ratings / 2
                    tvStoryline?.text = movie.overview
                    Glide.with(ivHeader!!).load(movie.poster).into(ivHeader)
                }
            }
        }
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