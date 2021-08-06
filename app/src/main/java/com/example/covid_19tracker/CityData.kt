package com.example.covid_19tracker

data class CityData(
    var active: Int,
    var confirmed: Int,
    var deceased: Int,
    var recovered: Int
)