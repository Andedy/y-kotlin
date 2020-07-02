package br.com.cryps.http

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import br.com.cryps.classes.Coin
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

object httpCoin {

    var Json_URL = "https://www.mercadobitcoin.net/api/"

    @SuppressLint("ServiceCast")
    fun hasConnetcion(ctx: Context):Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info =cm.activeNetworkInfo
        return info!= null && info.isConnected
    }

    fun loadCoin(coin: String): Coin? {
        val client = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url(Json_URL +coin+"/ticker/")
            .build()
        val response = client.newCall(request).execute()
        val jsonString = response.body?.string()
        val jsonO = JSONObject(jsonString)
        val jsonTicker = jsonO.getJSONObject("ticker")
        return readJson(jsonTicker)
    }

    fun readJson(json: JSONObject): Coin?{

        try {
            var coin = Coin(
                json.getDouble("high"),
                json.getDouble("low"),
                json.getDouble("vol"),
                json.getDouble("last"),
                json.getDouble("buy"),
                json.getDouble("sell"),
                json.getString("date")
            )
            return coin
        }catch (e: IOException){
            Log.e("Error", "Impossivel ler Json")
        }
        return null
    }

}