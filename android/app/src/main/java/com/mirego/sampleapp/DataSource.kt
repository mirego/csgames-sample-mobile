package com.mirego.sampleapp

import java.util.*

interface DataSource {
    interface RefreshCallback {
        fun onRefreshFinished()
    }

    fun refreshData()

    fun getCurrentDate(): Date

    fun setRefreshCallback(refreshCallback: RefreshCallback)
}