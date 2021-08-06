package com.example.covid_19tracker

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsfeedapp.MySingleton
import kotlinx.android.synthetic.main.activity_cities.*
import java.util.*
import kotlin.collections.ArrayList


class Cities : AppCompatActivity() {

    companion object{
        const val State_name = ""
    }
    private lateinit var state: String
    private val citydata : ArrayList<CityData> = ArrayList()
    private val city : ArrayList<String> = ArrayList()
    private lateinit var cAdapter: CityRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
         state = intent.getStringExtra(State_name).toString()
        cityRecyclerView.layoutManager = LinearLayoutManager(this)
        fetch()
        cAdapter = CityRecyclerAdapter(this)
        cityRecyclerView.adapter = cAdapter

}

    fun fetch(){
        val url = "https://api.covid19india.org/state_district_wise.json"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                val stateJsonObject = it.getJSONObject(state)
                val district = stateJsonObject.getJSONObject("districtData")
                val keys : Iterator<String> = district.keys()
                while(keys.hasNext()){
                    val cityName = keys.next()
                    city.add(cityName)
                    val c = district.getJSONObject(cityName)
                    val city = CityData(
                        c.getInt("active"),
                        c.getInt("confirmed"),
                        c.getInt("deceased"),
                        c.getInt("recovered")
                    )
                    citydata.add(city)
                }
                cAdapter.updatedata(citydata,city)
            },
            Response.ErrorListener {
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}
