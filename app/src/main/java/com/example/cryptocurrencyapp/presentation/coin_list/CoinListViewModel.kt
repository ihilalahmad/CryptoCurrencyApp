package com.example.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyappyt.common.Resource
import com.example.cryptocurrencyappyt.domain.use_cases.get_coins_list.GetCoinsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsListUseCase: GetCoinsListUseCase
) : ViewModel() {

    private val _coinListState = mutableStateOf(CoinsListState())
    val coinListState: State<CoinsListState> = _coinListState

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coinListState.value = CoinsListState(
                        coins = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _coinListState.value = CoinsListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _coinListState.value = CoinsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}