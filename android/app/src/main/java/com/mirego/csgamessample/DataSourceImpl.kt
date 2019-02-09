package com.mirego.csgamessample

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class DataSourceImpl : DataSource {
    companion object {
        private const val TIME_API_URL = "https://script.google.com/macros/s/AKfycbyd5AcbAnWi2Yn0xhFRbyzS4qMq1VucMVgVvhul5XqS9HkAyJY/exec?tz=America/Montreal"
        private const val TIME_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z"
    }

    private val timeLocale = Locale.ENGLISH
    private val dateFormat = SimpleDateFormat(TIME_FORMAT, timeLocale)
    private val client = OkHttpClient()

    private var currentDate = Calendar.getInstance().time
    private var refreshCallback: DataSource.RefreshCallback? = null

    override fun refreshData() {
        val request = Request.Builder()
            .url(TIME_API_URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                currentDate = null
                refreshCallback()
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonData = response.body()?.string()
                    val jsonObject = JSONObject(jsonData)
                    val fullDate = jsonObject.getString("fulldate")
                    currentDate = dateFormat.parse(fullDate)
                } catch (e: Exception) {
                    currentDate = null
                }

                refreshCallback()
            }
        })
    }

    override fun getCurrentDate(): Date = currentDate

    override fun setRefreshCallback(refreshCallback: DataSource.RefreshCallback) {
        this.refreshCallback = refreshCallback
    }

    private fun refreshCallback() {
        refreshCallback?.onRefreshFinished()
    }
}