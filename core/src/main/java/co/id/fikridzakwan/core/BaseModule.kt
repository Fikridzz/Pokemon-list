package co.id.fikridzakwan.core

import co.id.fikridzakwan.data.repository.PokemonDataStore
import co.id.fikridzakwan.data.repository.PokemonRepository
import co.id.fikridzakwan.data.source.network.PokemonApi
import co.id.fikridzakwan.domain.usecase.PokemonInteractor
import co.id.fikridzakwan.domain.usecase.PokemonUseCase
import org.koin.dsl.module

val baseModule = module {

    single { PokemonApi(get()) }

    single<PokemonRepository> { PokemonDataStore(get(), get()) }

    factory<PokemonUseCase> { PokemonInteractor(get()) }
}