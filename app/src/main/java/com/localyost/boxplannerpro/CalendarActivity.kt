package com.localyost.boxplannerpro

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.localyost.boxplannerpro.constants.SettingStrings
import com.localyost.boxplannerpro.constants.Tracks
import com.localyost.boxplannerpro.remote.ApiClient
import com.localyost.boxplannerpro.remote.ApiService
import com.localyost.boxplannerpro.remote.data.CrossfitClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class CalendarActivity : AppCompatActivity() {

    val classesMap = mutableMapOf<LocalDate, ArrayList<CrossfitClass>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        setCrossfitClasses()
        val calendarView = findViewById<View>(R.id.calendar) as CalendarView

        calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->

            })
    }

    private fun setCrossfitClasses() {
        val apiInterface = ApiClient.getClient().create(ApiService::class.java)
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)

        val dateOfClass = LocalDate.now().plusWeeks(1)

        val queryParams = mapOf("date" to dateOfClass.toString(), "boxId" to "d37974e3-d6c0-4470-ae02-41d1b0c0ca86")
        val call = apiInterface.getClasses(sharedPreferences.getString(SettingStrings.TOKEN, ""), queryParams)
        call.enqueue(object : Callback<List<CrossfitClass>> {
            override fun onResponse(
                call: Call<List<CrossfitClass>>,
                response: Response<List<CrossfitClass>>
            ) {

                response.body()
                    ?.filter { !Tracks.isOpenGym(it) }
                    ?.filter { it.getDate().isAfter(dateOfClass)}
                    ?.forEach {
                        if (classesMap[it.getDateStart()] == null) {
                            classesMap[it.getDateStart()] = arrayListOf(it)
                        } else {
                            classesMap[it.getDateStart()]?.add(it)
                        }
                    }
                println()
            }

            override fun onFailure(call: Call<List<CrossfitClass>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}