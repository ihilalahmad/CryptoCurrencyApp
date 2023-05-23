package com.plcoding.cryptocurrencyappyt.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyappyt.common.Constants
import com.example.cryptocurrencyappyt.common.Resource
import com.example.cryptocurrencyappyt.domain.use_cases.get_coin_details.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinDetailState = mutableStateOf(CoinsDetailsState())
    val coinDetailState: State<CoinsDetailsState> = _coinDetailState


    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoins(coinId)
        }
    }

    private fun getCoins(coinId: String) {
        getCoinDetailsUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coinDetailState.value = CoinsDetailsState(
                        coin = result.data
                    )
                }

                is Resource.Error -> {
                    _coinDetailState.value = CoinsDetailsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _coinDetailState.value = CoinsDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}