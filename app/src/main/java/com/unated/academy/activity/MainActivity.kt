package com.unated.academy.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.unated.academy.R
import com.unated.academy.appComponent
import com.unated.academy.presentation.moviedetails.FragmentMoviesDetails
import com.unated.academy.presentation.movielist.FragmentMoviesList
import com.unated.academy.interfaces.NavigationListener

class MainActivity : AppCompatActivity(),
    NavigationListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, appComponent().getFactory())[MainViewModel::class.java]

        if (savedInstanceState == null) {
            viewModel.configuration.observe(
                this,
                {
                    it?.let { configuration ->
                        addFragment(
                            R.id.container,
                            FragmentMoviesList.newInstance(configuration)
                        )

                        if (intent != null) handleIntent(intent)
                    }
                })
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) handleIntent(intent)
    }

    override fun goToDetails(id: Int) {
        viewModel.configuration.value?.let { configuration ->
            replaceFragment(
                R.id.container,
                FragmentMoviesDetails.newInstance(id, configuration)
            )
        }
    }

    override fun goToMain() {
        popFragment()
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val id = intent.data?.lastPathSegment?.toIntOrNull()
                if (id != null) {
                    goToDetails(id)
                }
            }
        }
    }
}

fun AppCompatActivity.popFragment() = supportFragmentManager.popBackStack()

fun AppCompatActivity.replaceFragment(container: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().addToBackStack(null).replace(container, fragment)
        .commit()

fun AppCompatActivity.addFragment(container: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().add(container, fragment).commit()