package co.id.fikridzakwan.data.source.network

import co.id.fikridzakwan.data.source.response.ListPokemonResponse
import co.id.fikridzakwan.data.source.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiClient {

    @GET("pokemon/{id}")
    suspend fun getDetailPokemon(@Path ("id")id: Int): Response<PokemonResponse>

    @GET("pokemon")
    suspend fun getListPokemon(
        @Query ("offset") offset: Int,
        @Query ("limit") limit: Int = 20
    ): Response<ListPokemonResponse>
}