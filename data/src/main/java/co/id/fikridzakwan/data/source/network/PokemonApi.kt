package co.id.fikridzakwan.data.source.network

import co.id.fikridzakwan.data.source.response.ListPokemonResponse
import co.id.fikridzakwan.data.source.response.PokemonResponse
import retrofit2.Response

class PokemonApi(private val pokemonApi: PokemonApiClient) : PokemonApiClient {
    override suspend fun getDetailPokemon(id: Int): Response<PokemonResponse> = pokemonApi.getDetailPokemon(id)

    override suspend fun getListPokemon(offset: Int, limit: Int): Response<ListPokemonResponse> = pokemonApi.getListPokemon(offset, limit)
}