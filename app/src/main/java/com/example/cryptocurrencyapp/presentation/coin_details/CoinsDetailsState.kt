package com.plcoding.cryptocurrencyappyt.presentation.coin_details

import com.example.cryptocurrencyapp.domain.models.CoinDetail

data class CoinsDetailsState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
