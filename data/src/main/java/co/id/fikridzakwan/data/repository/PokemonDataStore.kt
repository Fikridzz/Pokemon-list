package co.id.fikridzakwan.data.repository

import co.id.fikridzakwan.common.ReqStatus
import co.id.fikridzakwan.data.source.db.PokemonDao
import co.id.fikridzakwan.data.source.entities.PokemonEntity
import co.id.fikridzakwan.data.source.network.PokemonApi
import co.id.fikridzakwan.data.source.network.PokemonApiClient
import co.id.fikridzakwan.data.source.response.ListPokemonResponse
import co.id.fikridzakwan.data.source.response.PokemonResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonDataStore(private val pokemonApi: PokemonApi, private val pokemonDao: PokemonDao) : PokemonRepository {

    override suspend fun getListPokemon(offset: Int): Flow<ListPokemonResponse?> {
        return flow {
            val response = pokemonApi.getListPokemon(offset)
            emit(response.body())
        }
    }

    override suspend fun getDetailPokemon(id: Int): Flow<PokemonResponse?> {
        return flow {
            val response = pokemonApi.getDetailPokemon(id)
            emit(response.body())
        }
    }

    override suspend fun getSavedPokemon(): Flow<List<PokemonEntity>> {
        return pokemonDao.getSavedPokemon()
    }

    override suspend fun getSavePokemon(id: Int): Flow<PokemonEntity> {
        return pokemonDao.getSavePokemon(id)
    }

    override suspend fun insertPokemon(data: PokemonEntity) {
        return pokemonDao.insertPokemon(data)
    }

    override suspend fun deleteSavePokemon(id: Int) {
        return pokemonDao.deleteSavePokemon(id)
    }
}