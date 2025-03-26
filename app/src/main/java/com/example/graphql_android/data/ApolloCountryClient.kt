package com.example.graphql_android.data

import com.apollographql.apollo.ApolloClient
import com.example.graphql_android.CountriesQuery
import com.example.graphql_android.CountryQuery
import com.example.graphql_android.data.model.toCountryDetail
import com.example.graphql_android.data.model.toSimpleCountry
import com.example.graphql_android.domain.CountryClient
import com.example.graphql_android.domain.model.CountryDetail
import com.example.graphql_android.domain.model.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountryDetail(code: String): CountryDetail? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toCountryDetail()
    }
}