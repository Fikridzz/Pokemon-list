package co.id.fikridzakwan.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.id.fikridzakwan.data.source.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}