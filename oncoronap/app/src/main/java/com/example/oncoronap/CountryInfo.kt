package com.example.oncoronapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info.*

class CountryInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val array = this.intent.getParcelableExtra<Countries>("info")

        place_name.text = array.name
        cases.text = resources.getString(R.string.cases) + " " + array.cases.toString()
        date.text = array.date
        confirmed.text = resources.getString(R.string.confirmed) + " " + array.confirmed.toString()
        deaths.text = resources.getString(R.string.deaths) + " " + array.deaths.toString()
        recovered.text = resources.getString(R.string.recovered) + " " + array.recovered.toString()

        bluebig.setOnClickListener() {
            val intent = Intent(this, ActivityCountryList::class.java)
            startActivity(intent)
        }


    }




}
