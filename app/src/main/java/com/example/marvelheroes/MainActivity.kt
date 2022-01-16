package com.example.marvelheroes

import Network.Heroes
import Network.RetrofitClient
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    var heroes: ListView? = null
    /*var myHero: TextView = findViewById(R.id.oneHero)*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        heroes = findViewById<ListView>(R.id.superListView)

        getSuperHeroes()
    }

    private fun getSuperHeroes() {
        val call: Call<List<Heroes>> = RetrofitClient().myApi.getSuperHeroes()
        call.enqueue(object : Callback<List<Heroes>> {
            override fun onResponse(call: Call<List<Heroes>>, response: Response<List<Heroes>>) {
                val myheroList: List<Heroes> = response.body()!!
                val max: Int = myheroList.size - 1
                val randomIndex: Int = (Math.random() * (max - 1)).toInt()
                val oneHeroes = arrayOfNulls<String>(1)

               /* for (i in myheroList.indices) {
                    oneHeroes[i] = myheroList[i].name
                }*/

                oneHeroes[0] = myheroList[randomIndex].name
                
                heroes?.adapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_list_item_1,
                    oneHeroes
                )
            }
            override fun onFailure(call: Call<List<Heroes>>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }

}