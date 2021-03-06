package com.example.oncoronapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class CountryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val array = this.intent.getParcelableExtra<Countries>("info")

        place_name.text = array.name
        date.text = array.date
        confirmed.text = resources.getString(R.string.cases) + " " + array.cases.toString()
        confirmed.text = resources.getString(R.string.confirmed) + " " + array.confirmed.toString()
        deaths.text = resources.getString(R.string.deaths) + " " + array.deaths.toString()
        recovered.text = resources.getString(R.string.recovered) + " " + array.recovered.toString()
    }
}
