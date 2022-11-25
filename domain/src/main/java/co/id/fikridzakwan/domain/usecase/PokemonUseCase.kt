package co.id.fikridzakwan.domain.usecase

import co.id.fikridzakwan.data.source.entities.PokemonEntity
import co.id.fikridzakwan.domain.model.ListPokemon
import co.id.fikridzakwan.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {

    suspend fun getListPokemon(offset: Int): Flow<List<ListPokemon?>>

    suspend fun getDetailPokemon(id: Int): Flow<Pokemon?>

    suspend fun getSavedPokemon(): Flow<List<ListPokemon>>

    suspend fun getSavePokemon(id: Int): Flow<Pokemon>

    suspend fun insertPokemon(data: Pokemon)

    suspend fun deleteSavePokemon(id: Int)

}