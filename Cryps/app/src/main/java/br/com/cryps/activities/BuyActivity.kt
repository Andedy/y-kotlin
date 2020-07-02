package br.com.cryps.activities

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.cryps.classes.Coin
import br.com.cryps.MainActivity
import br.com.cryps.R
import br.com.cryps.db.Ativo
import br.com.cryps.db.AtivoDao
import br.com.cryps.http.httpCoin
import kotlinx.android.synthetic.main.activity_buy.*

class BuyActivity : AppCompatActivity() {

    var apelido : String ="BTC"
    var nome: String ="Bitcoin"
    private var asyncTask: CoinTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        apelido = intent.getStringExtra("apelido")
        nome = intent.getStringExtra("nome")
        txtNome.text = nome

        carregarDados()

        btnAdd.setOnClickListener(View.OnClickListener {

            var ativo = Ativo(null,txtNome.text.toString(),apelido,txtValor.text.toString().toDouble(),edtQdt.text.toString().toDouble(),
                total(txtValor.text.toString().toDouble(), edtQdt.text.toString().toDouble()),edtData.text.toString())
            var ativoDao = AtivoDao(this)
            ativoDao.insert(ativo)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

    }

    fun carregarDados(){
        if(asyncTask==null){
            if (httpCoin.hasConnetcion(this)){
                if(asyncTask?.status!=AsyncTask.Status.RUNNING){
                    asyncTask = CoinTask()
                    asyncTask?.execute()
                }
            }
        }
    }

    inner class CoinTask: AsyncTask<Void, Void, Coin?>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void?): Coin? {
            return httpCoin.loadCoin(apelido)
        }

        private fun update(result: Coin?){
            if(result != null){
                txtValor.text = result.high.toString()
            }

            asyncTask = null
        }

        override fun onPostExecute(result: Coin?) {
            super.onPostExecute(result)
            update(result as Coin?)
        }

    }

    fun total(valor: Double, qtd: Double): Double {
        val total = valor*qtd
        return total
    }
}