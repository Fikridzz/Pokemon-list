package co.id.fikridzakwan.mypokemonlist.util

import android.app.Application
import co.id.fikridzakwan.core.baseModule
import co.id.fikridzakwan.core.databaseModule
import co.id.fikridzakwan.core.networkModule
import co.id.fikridzakwan.presentation.di.detailPokemonModule
import co.id.fikridzakwan.presentation.di.listPokemonModule
import co.id.fikridzakwan.presentation.di.listSavePokemonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    baseModule,
                    listPokemonModule,
                    detailPokemonModule,
                    listSavePokemonModule
                )
            )
        }
    }
}