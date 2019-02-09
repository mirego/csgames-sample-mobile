package com.mirego.csgamessample

import java.util.*

interface DataSource {
    interface RefreshCallback {
        fun onRefreshFinished()
    }

    fun refreshData()

    fun getCurrentDate(): Date

    fun setRefreshCallback(refreshCallback: RefreshCallback)
}