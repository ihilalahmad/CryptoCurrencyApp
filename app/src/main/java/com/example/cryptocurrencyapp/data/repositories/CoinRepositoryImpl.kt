package com.example.cryptocurrencyapp.data.repositories

import com.example.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrencyapp.data.remote.dto.CoinDto
import com.example.cryptocurrencyappyt.domain.repositories.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val myApi: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return myApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return myApi.getCoinDetails(coinId)
    }
}