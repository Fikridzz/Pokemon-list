package co.id.fikridzakwan.data.source.response

data class ListPokemonResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String?,
    val url: String
)