package com.example.marvel.di

import com.example.data.retrofit.NetworkModule
import com.example.marvel.presentation.activity.MarvelCharacterListActivity
import com.example.marvel.presentation.fragment.CharacterListFragment
import com.example.data.datasource.CharacterModule
import com.example.marvel.BaseApplication
import com.example.marvel.presentation.CharacterListPresenter
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        CharacterModule::class,
        NetworkModule::class
    ])
//        ,PresentationModule::class])
interface CharactersListComponent: AndroidInjector<BaseApplication> {
    fun inject(activity: MarvelCharacterListActivity)
    fun inject(fragment: CharacterListFragment)
    fun inject(presenter: CharacterListPresenter)
}

//@ForApplication
//@Singleton
//@Component
//interface AppComponent : AndroidInjector<BaseApplication> {
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: AppModule): Builder
//        fun build(): AppComponent
//    }
//}