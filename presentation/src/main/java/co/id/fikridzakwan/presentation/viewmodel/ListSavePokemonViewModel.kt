package co.id.fikridzakwan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.fikridzakwan.common.Resource
import co.id.fikridzakwan.domain.model.ListPokemon
import co.id.fikridzakwan.domain.model.Pokemon
import co.id.fikridzakwan.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListSavePokemonViewModel(private val pokemonUseCase: PokemonUseCase) : ViewModel() {

    private val _savedPokemon = MutableStateFlow<Resource<List<ListPokemon>>>(Resource.Init())
    val savedPokemon get() = _savedPokemon.asStateFlow()

    fun getSavedPokemon() {
        viewModelScope.launch {
            _savedPokemon.value = Resource.Loading()

            pokemonUseCase.getSavedPokemon()
                .catch { _savedPokemon.value = Resource.Error(it.localizedMessage) }
                .collectLatest { _savedPokemon.value = Resource.Success(it) }
        }
    }
}