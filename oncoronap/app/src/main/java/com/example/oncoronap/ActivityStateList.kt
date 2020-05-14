package com.example.oncoronapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oncoronapp.API.HTTP_states
import com.example.oncoronapp.AdapterStates
import com.example.oncoronapp.R
import com.example.oncoronapp.States
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_state_list.*

class ActivityStateList : AppCompatActivity() {
    private var stateList = arrayListOf<States>()
    private var adapter = AdapterStates(stateList)
    private var asyncTask : StatesTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_list)
        carregaDados()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter

        tv_world.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ActivityCountryList::class.java)
            startActivity(intent)
        })

    }



    fun showProgress(show: Boolean){
        if(show){
            error_display.text = "Loading ..."
        }else{
            error_display.visibility = if(show) View.VISIBLE else View.GONE
            progress_bar.visibility = if(show) View.VISIBLE else View.GONE
        }
    }

    fun carregaDados(){
        stateList.clear()
        if(stateList.isNotEmpty()){
            showProgress(false)
        }else{
            if(asyncTask==null){
                if(HTTP_states.hasConnection(this)){
                    if(asyncTask?.status != AsyncTask.Status.RUNNING){
                        asyncTask = StatesTask()
                        asyncTask?.execute()
                    }
                }else{
                    progress_bar.visibility = View.GONE
                }
            }else if(asyncTask?.status==AsyncTask.Status.RUNNING){
                showProgress(true)
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class StatesTask: AsyncTask<Void, Void, List<States?>>(){
        override fun onPreExecute() {
            super.onPreExecute()
            showProgress(true)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<States>? {
            return HTTP_states.loadStates()
        }

        private fun update(result: List<States>?){
            if(result != null){
                stateList.clear()
                stateList.addAll(result)
            }else{
                error_display.text = "Loading Error"
            }
            adapter.notifyDataSetChanged()
            asyncTask = null
        }

        override fun onPostExecute(result: List<States?>?) {
            super.onPostExecute(result)
            showProgress(false)
            update(result as List<States>?)
        }
    }
}
