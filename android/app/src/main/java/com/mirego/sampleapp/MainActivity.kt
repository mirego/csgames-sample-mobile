package com.mirego.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TIMER_INTERVAL = 1000L
    }

    private val dataSource = DataSourceImpl()

    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataSource.setRefreshCallback(object : DataSource.RefreshCallback {
            override fun onRefreshFinished() {
                runOnUiThread { displayDate(dataSource.getCurrentDate()) }
            }
        })
    }

    public override fun onStop() {
        super.onStop()
        cancelTimer()
    }

    public override fun onStart() {
        super.onStart()
        timer = Timer()
        startTimer()
    }

    private fun startTimer() {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                dataSource.refreshData()
            }
        }, 0, TIMER_INTERVAL)
    }

    private fun cancelTimer() {
        timer.cancel()
        timer.purge()
    }

    private fun displayDate(date: Date) {
        weekDay.text = DateFormat.format("EEEE", date)
        day.text = DateFormat.format("dd", date)
        month.text = DateFormat.format("MMMM", date)
        time.text = DateFormat.format("HH'h' mm'm' ss's'", date)
    }
}
