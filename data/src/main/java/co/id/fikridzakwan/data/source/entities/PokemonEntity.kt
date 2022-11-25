package co.id.fikridzakwan.data.source.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_entities")
data class PokemonEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name = "height")
    val height: Int?,

    @ColumnInfo(name = "weight")
    val weight: Int?,

    @ColumnInfo(name = "species")
    val species: String?,

    @ColumnInfo(name = "types")
    val types: String?,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
)