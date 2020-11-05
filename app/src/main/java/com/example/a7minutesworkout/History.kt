package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_b_m_i.*
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history)
        val actionBar = supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "History"
        }

        toolbar_history.setNavigationOnClickListener{
            onBackPressed()
        }

        getAllCompletedDates()
    }

    private fun getAllCompletedDates() {

        // Instance of the Sqlite Open Helper class.
        val dbHandler = sqliteOpenHelper(this, null)

        val allCompletedDatesList =
            dbHandler.getAllCompletedDatesList() // List of history table data

            if (allCompletedDatesList.size > 0) {
                // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
                tvHistory.visibility = View.VISIBLE
                rvHistory.visibility = View.VISIBLE
                tvNoDataAvailable.visibility = View.GONE

                // Creates a vertical Layout Manager
                rvHistory.layoutManager = LinearLayoutManager(this)

                // History adapter is initialized and the list is passed in the param.
                val historyAdapter = HistoryAdapter(this, allCompletedDatesList)

                // Access the RecyclerView Adapter and load the data into it
                rvHistory.adapter = historyAdapter
            } else {
                tvHistory.visibility = View.GONE
                rvHistory.visibility = View.GONE
                tvNoDataAvailable.visibility = View.VISIBLE
            }
    }
}