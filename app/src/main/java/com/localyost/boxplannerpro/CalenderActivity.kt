package com.localyost.boxplannerpro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.localyost.boxplannerpro.constants.SettingStrings
import com.localyost.boxplannerpro.remote.ApiClient
import com.localyost.boxplannerpro.remote.ApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalenderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        val apiInterface = ApiClient.getClient().create(ApiService::class.java)
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val queryParams = mapOf("date" to "2023-09-20", "boxId" to "d37974e3-d6c0-4470-ae02-41d1b0c0ca86")
        val call = apiInterface.getClasses(sharedPreferences.getString(SettingStrings.TOKEN, ""), queryParams)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                println()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}