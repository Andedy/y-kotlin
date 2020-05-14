package com.example.oncoronapp

import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oncoronapp.AdapterCountries
import com.example.oncoronapp.HTTP_countries
import com.example.oncoronapp.R
import com.example.oncoronapp.Countries
import kotlinx.android.synthetic.main.activity_country_list.*
import kotlinx.android.synthetic.main.activity_country_list.error_display
import kotlinx.android.synthetic.main.activity_country_list.progress_bar
import kotlinx.android.synthetic.main.activity_country_list.recycler
import kotlinx.android.synthetic.main.activity_country_list.tv_world
import kotlinx.android.synthetic.main.activity_state_list.*

class ActivityCountryList : AppCompatActivity() {

    private var listaPaises = arrayListOf<Countries>()
    private var adapter =
        AdapterCountries(listaPaises)
    private var asyncTask : CountryTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        carregaDados()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter

        tv_brasil.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ActivityStateList::class.java)
            startActivity(intent)
        })

        tv_world.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ActivityCountryList::class.java)
            startActivity(intent)
        })
    }

    fun showProgress(show: Boolean){
        if(show){
            error_display.text = "Loading..."
        }else{
            error_display.visibility = if(show) View.VISIBLE else View.GONE
            progress_bar.visibility = if(show) View.VISIBLE else View.GONE
        }
    }

    fun carregaDados(){
        listaPaises.clear()
        if(listaPaises.isNotEmpty()){
            showProgress(false)
        }else{
            if(asyncTask==null){
                if(HTTP_countries.hasConnection(this)){
                    if(asyncTask?.status != AsyncTask.Status.RUNNING){
                        asyncTask = CountryTask()
                        asyncTask?.execute()
                    }
                }else{
                    progress_bar.visibility = View.GONE
                }
            }else if(asyncTask?.status== AsyncTask.Status.RUNNING){
                showProgress(true)
            }
        }
    }

    inner class CountryTask: AsyncTask<Void, Void, List<Countries?>>(){
        override fun onPreExecute() {
            super.onPreExecute()
            showProgress(true)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<Countries>? {
            return HTTP_countries.loadCountries()
        }

        private fun update(result: List<Countries>?){
            if(result != null){
                listaPaises.clear()
                listaPaises.addAll(result)
            }else{
                error_display.text = "Loading Error"
            }
            adapter.notifyDataSetChanged()
            asyncTask = null
        }

        override fun onPostExecute(result: List<Countries?>?) {
            super.onPostExecute(result)
            showProgress(false)
            update(result as List<Countries>?)
        }
    }
}