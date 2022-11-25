package co.id.fikridzakwan.data.source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.id.fikridzakwan.data.source.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_entities")
    fun getSavedPokemon(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_entities WHERE id= :id")
    fun getSavePokemon(id: Int): Flow<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(data: PokemonEntity)

    @Query("DELETE FROM pokemon_entities WHERE id= :id")
    suspend fun deleteSavePokemon(id: Int)
}