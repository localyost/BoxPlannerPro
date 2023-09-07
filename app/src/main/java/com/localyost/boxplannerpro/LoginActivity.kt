package com.localyost.boxplannerpro

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.localyost.boxplannerpro.constants.SettingStrings
import com.localyost.boxplannerpro.remote.ApiClient
import com.localyost.boxplannerpro.remote.ApiService
import com.localyost.boxplannerpro.remote.LoginBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var button = findViewById<Button>(R.id.submit)
        button.setOnClickListener {
            var email = findViewById<EditText>(R.id.email).text.toString()
            var password = findViewById<EditText>(R.id.password).text.toString()
            val apiInterface = ApiClient.getClient().create(ApiService::class.java)
//            val call = apiInterface.login(LoginBody(email, password))
            val call = apiInterface.login(LoginBody("yostpa@gmail.com", "C45X9bErQwXAUSJ"))
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.code() == 200) {
                        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
                        val token = response.raw().headers("Set-Cookie").find { cookie -> cookie.startsWith(".ASPXAUTH") }
                        if (!token.isNullOrBlank()) {
                            sharedPreferences.edit()
                                .putString(SettingStrings.EMAIL, email)
                                .putString(SettingStrings.PASSWORD, password)
                                .putString(SettingStrings.TOKEN, token)
                                .apply()
                        }

                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
    fun submitClick(view: View?) {

    }

}