package br.com.cryps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.cryps.activities.AddCoinActivity
import br.com.cryps.activities.ListCoinsActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnListCompra.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListCoinsActivity::class.java)
            startActivity(intent)
        })

        btnAdd.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddCoinActivity::class.java)
            startActivity(intent)
        })
    }
}