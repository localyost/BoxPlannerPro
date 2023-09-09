package com.localyost.boxplannerpro.remote.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.localyost.boxplannerpro.R

class CrossfitClassAdapter(private val context: Context, private val arrayList: ArrayList<CrossfitClass>?) : BaseAdapter() {
    private lateinit var className: TextView
    private lateinit var startdate: TextView
    override fun getCount(): Int {
        return arrayList?.size ?: 0
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.class_row, parent, false)
        className = convertView.findViewById(R.id.className)
        startdate = convertView.findViewById(R.id.startDate)
        if (arrayList != null) {
            val cfClass = arrayList[position]
            className.text = cfClass.getName()
            startdate.text = cfClass.getDateStart().toString()
        }


        return convertView
    }
}