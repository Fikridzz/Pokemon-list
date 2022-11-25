package co.id.fikridzakwan.domain.usecase

import co.id.fikridzakwan.data.repository.PokemonDataStore
import co.id.fikridzakwan.data.repository.PokemonRepository
import co.id.fikridzakwan.domain.DataMapper.toList
import co.id.fikridzakwan.domain.DataMapper.toListPokemon
import co.id.fikridzakwan.domain.DataMapper.toPokemon
import co.id.fikridzakwan.domain.DataMapper.toPokemonEntity
import co.id.fikridzakwan.domain.model.ListPokemon
import co.id.fikridzakwan.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonInteractor(private val pokemonDataStore: PokemonRepository): PokemonUseCase {

    override suspend fun getListPokemon(offset: Int): Flow<List<ListPokemon?>> {
        return pokemonDataStore.getListPokemon(offset).map { it?.results?.toListPokemon() ?: mutableListOf() }
    }

    override suspend fun getDetailPokemon(id: Int): Flow<Pokemon?> {
        return pokemonDataStore.getDetailPokemon(id).map { it?.toPokemon() }
    }

    override suspend fun getSavedPokemon(): Flow<List<ListPokemon>> {
        return pokemonDataStore.getSavedPokemon().map { it.toList() }
    }

    override suspend fun getSavePokemon(id: Int): Flow<Pokemon> {
        return pokemonDataStore.getSavePokemon(id).map { it.toPokemon() }
    }

    override suspend fun insertPokemon(data: Pokemon) {
        return pokemonDataStore.insertPokemon(data.toPokemonEntity())
    }

    override suspend fun deleteSavePokemon(id: Int) {
        return pokemonDataStore.deleteSavePokemon(id)
    }
}