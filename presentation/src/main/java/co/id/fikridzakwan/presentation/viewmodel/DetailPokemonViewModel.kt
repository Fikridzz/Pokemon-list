package co.id.fikridzakwan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.fikridzakwan.common.Resource
import co.id.fikridzakwan.domain.model.Pokemon
import co.id.fikridzakwan.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailPokemonViewModel(private val pokemonUseCase: PokemonUseCase) : ViewModel() {

    private val _pokemon = MutableStateFlow<Resource<Pokemon?>>(Resource.Init())
    val pokemon get() = _pokemon.asStateFlow()

    private val _dataFromDb = MutableStateFlow<Resource<Pokemon?>>(Resource.Init())
    val dataFromDb get() = _dataFromDb.asStateFlow()

    fun getDetailPokemon(id: Int) {
        viewModelScope.launch {
            _pokemon.value = Resource.Loading()

            pokemonUseCase.getDetailPokemon(id)
                .catch { _pokemon.value = Resource.Error(it.localizedMessage) }
                .collectLatest { _pokemon.value = Resource.Success(it) }
        }
    }

    fun getDetailPokemonFromDb(id: Int) {
        viewModelScope.launch {
            _dataFromDb.value = Resource.Loading()

            pokemonUseCase.getSavePokemon(id)
                .catch { _dataFromDb.value = Resource.Error(it.localizedMessage) }
                .collectLatest { _dataFromDb.value = Resource.Success(it) }
        }
    }

    fun savePokemon(data: Pokemon) {
        viewModelScope.launch {
            pokemonUseCase.insertPokemon(data)
        }
    }

    fun deletePokemon(id: Int) {
        viewModelScope.launch {
            pokemonUseCase.deleteSavePokemon(id)
        }
    }
}