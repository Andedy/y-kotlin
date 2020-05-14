package com.example.oncoronapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oncoronapp.R
import com.example.oncoronapp.StateInfo
import com.example.oncoronapp.States
import kotlinx.android.synthetic.main.card.view.*

class AdapterStates (private val states: List<States>): RecyclerView.Adapter<AdapterStates.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        val vh = VH(v)

        vh.itemView.setOnClickListener {
            val intent = Intent(v.context, StateInfo::class.java)
            val arrayStates = states[vh.adapterPosition]
            intent.putExtra("info", arrayStates)
            v.context.startActivity(intent)
        }

        return vh
    }

    override fun getItemCount(): Int {
        return states.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val state = states[position]
        holder.stateName.text = state.name
        holder.stateCases.text = holder.itemView.resources.getString(R.string.cases) + " " + state.cases.toString()
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stateName:TextView = itemView.card_place
        var stateCases:TextView = itemView.card_cases

    }
}