package com.unated.academy.presentation.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.unated.academy.interfaces.NavigationListener
import com.unated.academy.R
import com.unated.academy.appComponent

class FragmentMoviesList : Fragment() {

    private var navigationListener: NavigationListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter

    private lateinit var viewModel: MoviesViewModel

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

        viewModel = ViewModelProvider(this, appComponent().getFactory())[MoviesViewModel::class.java]

        recyclerView = view.findViewById(R.id.rv_movies)
        adapter = MoviesAdapter(listener)
        recyclerView.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, { adapter.setMovies(it) })
    }

    private var listener = object : MovieClickListener {
        override fun onMovieClicked(id: Int) {
            navigationListener?.goToDetails(id)
        }
    }
}

interface MovieClickListener {
    fun onMovieClicked(id: Int)
}