package co.id.fikridzakwan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.fikridzakwan.common.Resource
import co.id.fikridzakwan.domain.model.ListPokemon
import co.id.fikridzakwan.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListPokemonViewModel(private val pokemonUseCase: PokemonUseCase) : ViewModel() {

    private val _listPokemon = MutableStateFlow<Resource<List<ListPokemon?>>>(Resource.Init())
    val listPokemon get() = _listPokemon.asStateFlow()

    fun getListPokemon(offset: Int) {
        viewModelScope.launch {
            _listPokemon.value = Resource.Loading()

            pokemonUseCase.getListPokemon(offset)
                .catch { _listPokemon.value = Resource.Error(it.localizedMessage) }
                .collectLatest { _listPokemon.value = Resource.Success(it) }
        }
    }
}