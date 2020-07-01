package br.com.cryps.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cryps.AdapterCoin
import br.com.cryps.ListCoins
import br.com.cryps.R
import kotlinx.android.synthetic.main.activity_add_coin.*

class AddCoinActivity : AppCompatActivity() {

    val listCoin = arrayListOf<ListCoins>(
        ListCoins("Bitcoin", "BTC"),
        ListCoins("Litecoin", "LTC"),
        ListCoins("BCash", "BCH"),
        ListCoins("XRP (Ripple)", "XRP"),
        ListCoins("Ethereum", "ETH"),
        ListCoins("USD Coin", "USDC")
    )

    val adapter = AdapterCoin(listCoin)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coin)
        initRecycleView()
    }

    fun initRecycleView(){
        recycleCoin.adapter=adapter
        val layout = LinearLayoutManager(this)
        recycleCoin.layoutManager=layout
    }
}