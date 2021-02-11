package com.unated.academy.presentation.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unated.academy.R
import com.unated.academy.appComponent
import com.unated.academy.data.Configuration
import com.unated.academy.data.Movie
import com.unated.academy.data.MovieDetails
import com.unated.academy.interfaces.NavigationListener

class FragmentMoviesDetails : Fragment() {

    private var tvAge: TextView? = null
    private var tvTitle: TextView? = null
    private var tvGenre: TextView? = null
    private var tvReviews: TextView? = null
    private var rbRating: RatingBar? = null
    private var tvStoryline: TextView? = null
    private var rvActors: RecyclerView? = null
    private var ivHeader: ImageView? = null

    private var navigationListener: NavigationListener? = null
    private lateinit var configuration: Configuration

    private lateinit var viewModel: MovieDetailsViewModel

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

        viewModel =
            ViewModelProvider(this, appComponent().getFactory())[MovieDetailsViewModel::class.java]

        view.findViewById<LinearLayout>(R.id.llBack)
            .setOnClickListener { navigationListener?.goToMain() }

        view.findViewById<TextView>(R.id.tv_schedule).setOnClickListener {
            schedule()
        }

        viewModel.movie.observe(viewLifecycleOwner, { fillViews(it) })

        configuration = arguments?.getSerializable(EXTRA_CONFIGURATION) as Configuration
        arguments?.getInt(EXTRA_MOVIE_ID, 0)?.let { viewModel.getMovie(it) }
    }

    private fun schedule() {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, viewModel.movie.value?.title)
            putExtra(CalendarContract.Events.DESCRIPTION, viewModel.movie.value?.overview)
        }

        if(context?.packageManager?.let { intent.resolveActivity(it) } != null) {
            startActivity(intent)
        }
    }

    private fun fillViews(movie: MovieDetails) {
        val adapter = ActorsAdapter(configuration, movie.actors)
        rvActors?.adapter = adapter
        tvAge?.text = if (movie.adult) "16" else "13"
        tvTitle?.text = movie.title
        tvGenre?.text = movie.genres.joinToString(separator = ", ") { it.name }
        tvReviews?.text =
            context?.getString(R.string.title_movie_reviews_count, movie.numberOfRatings)
        rbRating?.rating = movie.ratings / 2
        tvStoryline?.text = movie.overview
        Glide.with(ivHeader!!)
            .load("${configuration.images.base_url}/${configuration.images.poster_sizes.last()}/${movie.poster}")
            .into(ivHeader!!)
    }

    companion object {

        private const val EXTRA_MOVIE_ID = "extra_movie_id"
        private const val EXTRA_CONFIGURATION = "extra_configuration"

        fun newInstance(id: Int, configuration: Configuration): FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails()
            fragment.arguments = Bundle().apply {
                putInt(EXTRA_MOVIE_ID, id)
                putSerializable(EXTRA_CONFIGURATION, configuration)
            }
            return fragment
        }
    }
}