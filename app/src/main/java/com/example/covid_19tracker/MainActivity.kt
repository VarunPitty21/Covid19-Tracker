package com.example.covid_19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val state = resources.getStringArray(R.array.State)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if(spinner != null){
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,state)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    fun onItemSelected (parent: AdapterView<*> , view : View , position : Int , id : Long){
                        Toast.makeText(this@MainActivity,"Selected State : " + state[position] , Toast.LENGTH_SHORT  ).show()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
    }
}
}