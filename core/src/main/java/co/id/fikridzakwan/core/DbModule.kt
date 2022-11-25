package co.id.fikridzakwan.core

import androidx.room.Room
import co.id.fikridzakwan.data.source.db.PokemonDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<PokemonDatabase>().pokemonDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            PokemonDatabase::class.java, "Pokemon.db"
        ).fallbackToDestructiveMigration().build()
    }
}