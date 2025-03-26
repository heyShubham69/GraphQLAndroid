package com.example.graphql_android.domain.useCases

import com.example.graphql_android.domain.CountryClient
import com.example.graphql_android.domain.model.SimpleCountry

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(): List<SimpleCountry> {
        return countryClient.getCountries()
            .sortedBy { it.name }
    }
}