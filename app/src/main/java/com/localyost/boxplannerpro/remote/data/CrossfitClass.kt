package com.localyost.boxplannerpro.remote.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class CrossfitClass(
    @SerializedName("Name")
    private val name: String,
    @SerializedName("TrackId")
    private val trackId: String,
    @SerializedName("Date")
    private val date: String,
    @SerializedName("Id")
    private val id: String,
    @SerializedName("DateStart")
    private val dateStart: String

){
    fun getDate(): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        return LocalDate.parse(date, formatter)
    }
    fun getDateStart(): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        return LocalDateTime.parse(dateStart, formatter)
    }

    fun getTrackId(): String {
        return trackId;
    }

    fun getName(): String {
        return name
    }
}