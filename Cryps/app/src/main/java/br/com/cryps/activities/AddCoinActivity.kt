package br.com.cryps.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cryps.adapter.AdapterCoin
import br.com.cryps.classes.ListCoins
import br.com.cryps.R
import kotlinx.android.synthetic.main.activity_add.*

class AddCoinActivity : AppCompatActivity() {

    val listCoin = arrayListOf<ListCoins>(
        ListCoins("Bitcoin", "BTC"),
        ListCoins("BCash", "BCH"),
        ListCoins("Litecoin", "LTC"),
        ListCoins("XRP (Ripple)", "XRP"),
        ListCoins("Ethereum", "ETH"),
        ListCoins("USD Dolar", "USDC")
    )

    val adapter = AdapterCoin(listCoin)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initRecycleView()
    }

    fun initRecycleView(){
        recycleCoin.adapter=adapter
        val layout = LinearLayoutManager(this)
        recycleCoin.layoutManager=layout
    }
}