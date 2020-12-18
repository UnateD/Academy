package com.unated.academy.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.unated.academy.DataProvider
import com.unated.academy.interfaces.NavigationListener
import com.unated.academy.R
import com.unated.academy.adapter.MoviesAdapter
import com.unated.academy.data.Movie
import com.unated.academy.data.loadMovies
import com.unated.academy.domain.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesList : BaseFragment() {

    private var navigationListener: NavigationListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter

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
        recyclerView = view.findViewById(R.id.rv_movies)

        adapter = MoviesAdapter(listener)
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch { getMovies() }
    }

    private suspend fun getMovies() = withContext(Dispatchers.IO) {
        fillViews(dataProvider?.dataSource()?.getMovies()!!)
    }

    private suspend fun fillViews(movies: ArrayList<Movie>) = withContext(Dispatchers.Main) {
        adapter.setMovies(movies)
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