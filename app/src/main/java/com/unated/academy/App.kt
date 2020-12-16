package com.unated.academy

import android.app.Application
import com.unated.academy.domain.DataSource

interface DataProvider {
    fun dataSource() : DataSource
}

class App : Application(), DataProvider {

    private lateinit var dataSource: DataSource

    override fun onCreate() {
        super.onCreate()

        dataSource = DataSource(this)
    }

    override fun dataSource() = dataSource
}