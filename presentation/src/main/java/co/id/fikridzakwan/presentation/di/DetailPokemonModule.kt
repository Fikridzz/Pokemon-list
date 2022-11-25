package co.id.fikridzakwan.presentation.di

import co.id.fikridzakwan.presentation.viewmodel.DetailPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailPokemonModule = module {
    viewModel { DetailPokemonViewModel(get()) }
}