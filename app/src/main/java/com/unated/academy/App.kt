package com.unated.academy

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.unated.academy.data.AppDatabase

class App : Application(), AppComponent {

    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        viewModelFactory = ViewModelFactory(MoviesRepository(AppDatabase.create(applicationContext)))
    }

    override fun getFactory() = viewModelFactory
}

fun Context.appComponent() = (applicationContext as App)
fun Fragment.appComponent() = requireContext().appComponent()
