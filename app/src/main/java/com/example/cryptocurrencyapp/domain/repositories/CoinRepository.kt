package com.example.cryptocurrencyappyt.domain.repositories

import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailsDto
}