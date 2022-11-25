package co.id.fikridzakwan.data.repository

import co.id.fikridzakwan.common.ReqStatus
import co.id.fikridzakwan.data.source.entities.PokemonEntity
import co.id.fikridzakwan.data.source.response.ListPokemonResponse
import co.id.fikridzakwan.data.source.response.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getListPokemon(offset: Int) : Flow<ListPokemonResponse?>

    suspend fun getDetailPokemon(id: Int) : Flow<PokemonResponse?>

    suspend fun getSavedPokemon(): Flow<List<PokemonEntity>>

    suspend fun getSavePokemon(id: Int): Flow<PokemonEntity>

    suspend fun insertPokemon(data: PokemonEntity)

    suspend fun deleteSavePokemon(id: Int)
}