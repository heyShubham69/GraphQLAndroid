package com.example.graphql_android.domain.model

data class CountryDetail(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val language: List<String>,
    val continent: String
)
