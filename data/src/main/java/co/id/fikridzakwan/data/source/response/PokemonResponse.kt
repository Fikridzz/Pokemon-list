package co.id.fikridzakwan.data.source.response

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val abilities: List<Ability>?,
    val id: Int?,
    val name: String?,
    val species: PokemonResult?,
    val stats: List<Stat>,
    val types: List<Type>,
    val height: Int?,
    val weight: Int?
)

data class Ability(
    val ability: PokemonResult?,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int?
)

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int?,
    val effort: Int?,
    val stat: PokemonResult?
)

data class Type(
    val slot: Int?,
    val type: PokemonResult?
)