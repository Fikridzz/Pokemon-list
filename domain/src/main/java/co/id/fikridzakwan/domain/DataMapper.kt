package co.id.fikridzakwan.domain

import co.id.fikridzakwan.data.source.entities.PokemonEntity
import co.id.fikridzakwan.data.source.response.PokemonResponse
import co.id.fikridzakwan.data.source.response.PokemonResult
import co.id.fikridzakwan.domain.DataMapper.toIdPokemon
import co.id.fikridzakwan.domain.model.ListPokemon
import co.id.fikridzakwan.domain.model.Pokemon

object DataMapper {

    fun List<PokemonResult>.toListPokemon(): List<ListPokemon> {
        val newData = mutableListOf<ListPokemon>()
        this.forEach {
            newData.add(it.toPokemonResult())
        }
        return newData
    }

    private fun PokemonResult.toPokemonResult() = ListPokemon(
        id = this.url.toIdPokemon(),
        name = this.name,
        nickname = null,
        url = this.url,
        imageUrl = this.url.toImage()
    )

    fun List<PokemonEntity>.toList(): List<ListPokemon> {
        val newData = mutableListOf<ListPokemon>()
        this.forEach {
            newData.add(it.toPokemonResult())
        }
        return newData
    }

    private fun PokemonEntity.toPokemonResult() = ListPokemon(
        id = this.id,
        name = this.name,
        nickname = this.nickname,
        url = null,
        imageUrl = this.imageUrl ?: ""
    )

    fun PokemonResponse?.toPokemon() = Pokemon(
        id = this?.id,
        name = this?.name,
        nickname = null,
        height = this?.height,
        weight = this?.weight,
        species = this?.species?.name,
        types = this?.types?.joinToString(", ") { it.type?.name.toString() },
        imageUrl = this?.id?.toImage()
    )

    fun PokemonEntity?.toPokemon() = Pokemon(
        id = this?.id,
        name = this?.name,
        nickname = this?.nickname,
        height = this?.height,
        weight = this?.weight,
        species = this?.species,
        types = this?.types,
        imageUrl = this?.imageUrl
    )

    fun Pokemon?.toPokemonEntity() = PokemonEntity(
        id = this?.id ?: 0,
        name = this?.name ?: "",
        nickname = this?.nickname ?: "",
        height = this?.height,
        weight = this?.weight,
        species = this?.species,
        types = this?.types,
        imageUrl = this?.imageUrl
    )

    private fun String.toIdPokemon(): Int {
        return split("/").last { it.isNotBlank() }.toInt()
    }

    private fun String.toImage(): String {
        val id = this.split("/").last { it.isNotBlank() }.toInt()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    }

    private fun Int.toImage(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${this}.png"
    }
}