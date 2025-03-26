package com.example.graphql_android.domain

import com.example.graphql_android.domain.model.CountryDetail
import com.example.graphql_android.domain.model.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountryDetail(code: String): CountryDetail?
}