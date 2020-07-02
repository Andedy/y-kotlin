package br.com.cryps.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cryps.classes.ListCoins
import br.com.cryps.R
import br.com.cryps.activities.BuyActivity
import kotlinx.android.synthetic.main.card_coin.view.*

class AdapterCoin(private val coins: List<ListCoins>):
    RecyclerView.Adapter<AdapterCoin.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_coin, parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val coin = coins[vh.adapterPosition]
            val intent = Intent(v.context, BuyActivity::class.java)
            intent.putExtra("nome", coin.name)
            intent.putExtra("apelido", coin.apelido)
            v.context.startActivities(arrayOf(intent))
        }
        return vh
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var coin = coins[position]
        holder.txtCoinName.text = coin.name
    }

    class VH(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtCoinName: TextView = itemView.txtCoinName
    }
}