package com.unated.academy.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.unated.academy.interfaces.NavigationListener
import com.unated.academy.R
import com.unated.academy.adapter.MoviesAdapter
import com.unated.academy.domain.DataSource

class FragmentMoviesList : Fragment() {

    private var navigationListener: NavigationListener? = null
    private lateinit var recyclerView: RecyclerView

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

        val adapter = MoviesAdapter(listener)
        adapter.setMovies(DataSource().getMovies())
        recyclerView.adapter = adapter
    }

    private var listener = object : (Int) -> Unit {
        override fun invoke(pos: Int) {
            navigationListener?.goToDetails(DataSource().getMovies()[pos].id)
        }
    }
}