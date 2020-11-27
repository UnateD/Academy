package com.unated.academy.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.unated.academy.NavigationListener
import com.unated.academy.R

class FragmentMoviesList : Fragment() {

    private var navigationListener: NavigationListener? = null

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
        view.findViewById<ConstraintLayout>(R.id.clMovieItem).setOnClickListener { navigationListener?.goToDetails() }
    }
}