package com.unated.academy.presentation.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.unated.academy.MoviesRepository
import com.unated.academy.MoviesWorker
import com.unated.academy.interfaces.NavigationListener
import com.unated.academy.R
import com.unated.academy.appComponent
import com.unated.academy.data.Configuration
import java.util.concurrent.TimeUnit

class FragmentMoviesList : Fragment() {

    private var navigationListener: NavigationListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter
    private lateinit var progressBar: ProgressBar

    private lateinit var viewModel: MoviesViewModel

    private val constraints = Constraints.Builder().setRequiresCharging(true).setRequiredNetworkType(
        NetworkType.UNMETERED).build()
    private val moviesWorker = PeriodicWorkRequest.Builder(MoviesWorker::class.java, 16, TimeUnit.MINUTES).setConstraints(constraints).build()

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
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        viewModel =
            ViewModelProvider(this, appComponent().getFactory())[MoviesViewModel::class.java]

        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork("moviesWork", ExistingPeriodicWorkPolicy.REPLACE, moviesWorker)

        progressBar = view.findViewById(R.id.progress_bar)
        recyclerView = view.findViewById(R.id.rv_movies)
        adapter = MoviesAdapter(arguments?.getSerializable(EXTRA_CONFIGURATION) as Configuration, listener)

        viewModel.movies.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            if(it) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.INVISIBLE
        })

        recyclerView.adapter = adapter
    }

    private var listener = object : MovieClickListener {
        override fun onMovieClicked(id: Int, sharedView: View) {
            navigationListener?.goToDetails(id, sharedView)
        }
    }

    companion object {
        fun newInstance(configuration: Configuration): FragmentMoviesList {
            val fragment = FragmentMoviesList()
            fragment.arguments = Bundle().apply {
                putSerializable(EXTRA_CONFIGURATION, configuration)
            }
            return fragment
        }

        const val EXTRA_CONFIGURATION = "configuration"
    }
}

interface MovieClickListener {
    fun onMovieClicked(id: Int, sharedView: View)
}