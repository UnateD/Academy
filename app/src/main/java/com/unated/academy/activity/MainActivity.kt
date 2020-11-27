package com.unated.academy.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unated.academy.NavigationListener
import com.unated.academy.R
import com.unated.academy.addFragment
import com.unated.academy.fragment.FragmentMoviesDetails
import com.unated.academy.fragment.FragmentMoviesList
import com.unated.academy.replaceFragment

class MainActivity : AppCompatActivity(), NavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) 

        if(savedInstanceState == null) {
            addFragment(R.id.container, FragmentMoviesList())
        }
    }

    override fun goToDetails() {
        replaceFragment(R.id.container, FragmentMoviesDetails())
    }
}