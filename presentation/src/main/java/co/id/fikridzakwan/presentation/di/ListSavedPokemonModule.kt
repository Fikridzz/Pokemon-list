package co.id.fikridzakwan.presentation.di

import co.id.fikridzakwan.presentation.viewmodel.ListSavePokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listSavePokemonModule = module {
    viewModel { ListSavePokemonViewModel(get()) }
}