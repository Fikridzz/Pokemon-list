package co.id.fikridzakwan.presentation.di

import co.id.fikridzakwan.presentation.viewmodel.ListPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listPokemonModule = module {
    viewModel { ListPokemonViewModel(get()) }
}