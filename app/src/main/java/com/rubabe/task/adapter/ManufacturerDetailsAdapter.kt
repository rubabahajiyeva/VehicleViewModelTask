package com.rubabe.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubabe.task.R
import com.rubabe.task.model.DTO
import com.rubabe.task.model.GetManufacturerDetails

class ManufacturerDetailsAdapter(private var list: List<GetManufacturerDetails>) :
    RecyclerView.Adapter<ManufacturerDetailsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var addressTextView = itemView.findViewById<TextView>(R.id.addressTextView)
        var cityTextView = itemView.findViewById<TextView>(R.id.cityTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_manufacturer_details_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val manufacturerDetail = list[position]
        holder.addressTextView.text = manufacturerDetail.Address
        holder.cityTextView.text = manufacturerDetail.City
    }

}