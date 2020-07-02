package br.com.cryps.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cryps.adapter.AdapterListCompra
import br.com.cryps.MainActivity
import br.com.cryps.R
import br.com.cryps.db.Ativo
import br.com.cryps.db.AtivoDao
import kotlinx.android.synthetic.main.activity_list_coins.*

class ListCoinsActivity : AppCompatActivity() {

    private var ativosList = mutableListOf<Ativo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_coins)
        updateAdapter()
        initRecycleView()

        btcoin.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        updateAdapter()
        initRecycleView()
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecycleView()
    }

    private fun updateAdapter(){
        val ativoDao = AtivoDao(this)
        ativosList.clear()
        ativosList = ativoDao.getAll()
        if (ativosList.isEmpty()){
            recycleListCompra.setVisibility(View.GONE)
            txtMsg.setVisibility(View.VISIBLE)
            txtMsg.setText("Sem dados para exibir")
        }else{
            recycleListCompra.setVisibility(View.VISIBLE)
            txtMsg.setVisibility(View.GONE)
        }

        recycleListCompra.adapter?.notifyDataSetChanged()
    }

    private fun initRecycleView(){
        val adapter2 = AdapterListCompra(ativosList)
        recycleListCompra.adapter = adapter2
        val layout = LinearLayoutManager(this)
        recycleListCompra.layoutManager = layout
    }


}