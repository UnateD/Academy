package com.unated.academy.presentation.movieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.unated.academy.presentation.NavigationListener
import com.unated.academy.R
import com.unated.academy.domain.provideDataSource

class MoviesListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var navigationListener: NavigationListener? = null
    private val dataSource = provideDataSource()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) {
            navigationListener = context
        }
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

        val adapter = MoviesAdapter(object : MovieClickListener {
            override fun onClick(movieId: Int) {
                navigationListener?.goToDetails(movieId)
            }
        })
        adapter.setMovies(dataSource.getMovies())
        recyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        navigationListener = null
    }
}