package com.example.oncoronapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.oncoronapp.ActivityStateList
import com.example.oncoronapp.ActivityCountryList
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_mundo.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ActivityCountryList::class.java)
            startActivity(intent)
        })

        tv_brasil.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ActivityStateList::class.java)
            startActivity(intent)
        })


    }

}
