package com.unated.academy.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.unated.academy.R
import com.unated.academy.presentation.moviedetails.MoviesDetailsFragment
import com.unated.academy.presentation.movieslist.MoviesListFragment

class MainActivity : AppCompatActivity(), NavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) 

        if(savedInstanceState == null) {
            addFragment(R.id.container, MoviesListFragment())
        }
    }

    override fun goToDetails(id: Int) {
        replaceFragment(R.id.container, MoviesDetailsFragment.newInstance(id))
    }

    override fun goToMain() {
        popFragment()
    }

    private fun replaceFragment(
        container: Int,
        fragment: Fragment
    ) = supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(container, fragment)
        .commit()

    private fun addFragment(
        container: Int,
        fragment: Fragment
    ) = supportFragmentManager.beginTransaction()
        .add(container, fragment)
        .commit()

    private fun popFragment() = supportFragmentManager.popBackStack()
}