package com.localyost.boxplannerpro

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.localyost.boxplannerpro.constants.SettingStrings
import com.localyost.boxplannerpro.constants.Tracks
import com.localyost.boxplannerpro.remote.ApiClient
import com.localyost.boxplannerpro.remote.ApiService
import com.localyost.boxplannerpro.remote.data.CrossfitClass
import com.localyost.boxplannerpro.remote.data.CrossfitClassAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate


class CalendarActivity : AppCompatActivity() {

    var classesMap = mutableMapOf<LocalDate, ArrayList<CrossfitClass>>()
    lateinit var calendarView: CalendarView
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        setCrossfitClasses()
        setListClickActions()
    }

    private fun setListClickActions() {
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener { parent, view, position, id ->
            val cfClass = parent.adapter.getItem(position)
            println(cfClass)
        }
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
                        if (classesMap[it.getDate()] == null) {
                            classesMap[it.getDate()] = arrayListOf(it)
                        } else {
                            classesMap[it.getDate()]?.add(it)
                        }
                    }

                calendarView = findViewById<View>(R.id.calendar) as CalendarView
                calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                    val selectedDate = LocalDate.of(year, month+1, dayOfMonth)
                    val classesOfDay = classesMap[selectedDate]

                    listView.adapter = CrossfitClassAdapter(this@CalendarActivity, classesOfDay)
                }
            }

            override fun onFailure(call: Call<List<CrossfitClass>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
