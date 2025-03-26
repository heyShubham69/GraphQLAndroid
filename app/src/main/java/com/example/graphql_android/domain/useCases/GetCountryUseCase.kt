package com.example.graphql_android.domain.useCases

import com.example.graphql_android.domain.CountryClient
import com.example.graphql_android.domain.model.CountryDetail

class GetCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code :String): CountryDetail?{
        return countryClient.getCountryDetail(code)
    }
}