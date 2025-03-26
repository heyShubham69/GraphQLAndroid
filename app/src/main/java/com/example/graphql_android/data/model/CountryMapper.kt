package com.example.graphql_android.data.model

import com.example.graphql_android.CountriesQuery
import com.example.graphql_android.CountryQuery
import com.example.graphql_android.domain.model.CountryDetail
import com.example.graphql_android.domain.model.SimpleCountry

fun CountryQuery.Country.toCountryDetail():CountryDetail{
    return CountryDetail(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital?:"No Capital",
        currency = currency?: "No Currency",
        continent = continent.name,
        language = languages.map { it.name }
    )
}
fun CountriesQuery.Country.toSimpleCountry():SimpleCountry{
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital?:"No Capital",
    )
}