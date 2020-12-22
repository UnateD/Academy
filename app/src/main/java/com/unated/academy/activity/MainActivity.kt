package com.unated.academy.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.unated.academy.R
import com.unated.academy.presentation.moviedetails.FragmentMoviesDetails
import com.unated.academy.presentation.movielist.FragmentMoviesList
import com.unated.academy.interfaces.NavigationListener

class MainActivity : AppCompatActivity(),
    NavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) 

        if(savedInstanceState == null) {
            addFragment(R.id.container, FragmentMoviesList())
        }
    }

    override fun goToDetails(id: Int) {
        replaceFragment(R.id.container, FragmentMoviesDetails.newInstance(id))
    }

    override fun goToMain() {
        popFragment()
    }
}

fun AppCompatActivity.popFragment() = supportFragmentManager.popBackStack()

fun AppCompatActivity.replaceFragment(container: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().addToBackStack(null).replace(container, fragment)
        .commit()

fun AppCompatActivity.addFragment(container: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().add(container, fragment).commit()