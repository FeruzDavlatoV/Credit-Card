package com.example.creditcard.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.creditcard.MainActivity
import com.example.creditcard.R
import com.example.creditcard.database.AppDatabase
import com.example.creditcard.model.Card
import com.example.creditcard.model.Responses
import com.example.creditcard.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViews()
    }

    private fun initViews() {
        val databaseSize = AppDatabase.getInstance(this).postDao().getCards().size
        if (databaseSize == 0){
            apiLoadCards()
        } else {
            callMainActivity()
        }
    }

    private fun apiLoadCards() {
        ApiClient.apiService.getAllCards().enqueue(object : Callback<ArrayList<Responses>> {
            override fun onResponse(
                call: Call<ArrayList<Responses>>,
                response: Response<ArrayList<Responses>>
            ) {
                for (i in response.body()!!){
                    val card = Card(i.cardHolder!!,i.cardNumber!!,i.expiredDate!!,true)
                    saveCardToDatabase(card)
                }
                callMainActivity()
            }

            override fun onFailure(call: Call<ArrayList<Responses>>, t: Throwable) {

            }

        })
    }

    private fun saveCardToDatabase(card: Card) {
        AppDatabase.getInstance(this).postDao().createCard(card)
    }
    fun callMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}