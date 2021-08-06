package com.example.covid_19tracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CityRecyclerAdapter(private val context : Context) : RecyclerView.Adapter<CityRecyclerAdapter.CityViewHolder>() {
    private val item : ArrayList<CityData> = ArrayList()
    private val city : ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.itemscitydata, parent, false)
        return CityViewHolder(View)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val currentitem = item[position]
        holder.mrecovered_count.text = currentitem.recovered.toString()
        holder.mdeath_count.text = currentitem.deceased.toString()
        holder.mconfirmed_count.text = currentitem.confirmed.toString()
        holder.mactive_count.text = currentitem.active.toString()
        holder.mcity.setText(city.get(position))
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mactive_count = itemView.findViewById<TextView>(R.id.activeCount)
        val mdeath_count = itemView.findViewById<TextView>(R.id.deathCount)
        val mrecovered_count = itemView.findViewById<TextView>(R.id.recoveredCount)
        val mconfirmed_count = itemView.findViewById<TextView>(R.id.confirmedCount)
        val mcity = itemView.findViewById<TextView>(R.id.cityName)
    }

    fun updatedata(updatedata : ArrayList<CityData>,updatecity: ArrayList<String>){
        item.clear()
        item.addAll(updatedata)
        city.clear()
        city.addAll(updatecity)
        notifyDataSetChanged()
    }
}



