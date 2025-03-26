package com.example.graphql_android.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.graphql_android.domain.model.CountryDetail
import com.example.graphql_android.domain.model.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit,
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding(),
        topBar = {
            Text(text = "Global countries", fontSize = 20.sp)
        },
        content = { paddingValues ->
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Loading...", fontSize = 30.sp)
                }
            }
            else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(state.countries.size) {
                        CountryItems(country = state.countries[it],
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelectCountry(state.countries[it].code)
                                }
                                .padding(16.dp))
                    }

                }
                if (state.selectedCountry != null) {
                    CountryDialog(
                        country = state.selectedCountry,
                        onDismiss = onDismissCountryDialog,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.White)
                            .padding(16.dp)
                    )
                }
            }

        })
}

@Composable
private fun CountryDialog(
    country: CountryDetail, onDismiss: () -> Unit, modifier: Modifier = Modifier
) {
    val joinedLanguages = remember(country.language) {
        country.language.joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = country.emoji, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = country.name, fontSize = 24.sp)
            }
            Text(text = "Continent: ${country.continent}")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Currency: ${country.currency}")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Capital: ${country.capital}")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Language(s): $joinedLanguages")
        }
    }
}

@Composable
fun CountryItems(
    country: SimpleCountry, modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
    Row(
            modifier = modifier, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = country.emoji, fontSize = 30.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = country.name, fontSize = 24.sp)
                Text(text = country.capital, fontSize = 24.sp)
            }

        }
    }
}
