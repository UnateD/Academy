package com.unated.academy

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment

class App : Application(), AppComponent {

    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        viewModelFactory = ViewModelFactory(MovieDataSource(this))
    }

    override fun getFactory() = viewModelFactory
}

fun Context.appComponent() = (applicationContext as App)
fun Fragment.appComponent() = requireContext().appComponent()
