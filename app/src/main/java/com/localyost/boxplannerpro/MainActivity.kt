package com.localyost.boxplannerpro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.localyost.boxplannerpro.constants.SettingStrings
import com.localyost.boxplannerpro.remote.ApiClient
import com.localyost.boxplannerpro.remote.ApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val loginToken = sharedPreferences.getString(SettingStrings.TOKEN, "");

        val apiInterface = ApiClient.getClient().create(ApiService::class.java)
        val call = apiInterface.isUserLoggedIn(loginToken)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val result = response.body()?.string()
                if (result == "false") {
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else{
                    val intent = Intent(this@MainActivity, CalenderActivity::class.java)
                    startActivity(intent)
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Handle network failure
            }
        })
    }
}