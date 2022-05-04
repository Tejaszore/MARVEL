//package com.example.marvel.data.injection
//
//import android.app.Application
//import dagger.android.AndroidInjector
//import com.example.marvel.MainActivity
//import dagger.BindsInstance
//import dagger.Component
//
//@Component
//interface AppComponent : AndroidInjector<MainActivity?> {
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application?): Builder?
//        fun build(): AppComponent?
//    }
//}

//package com.example.marvel.data.injection
//
//import android.app.Application
//import com.example.marvel.BaseApplication
//import dagger.BindsInstance
//import dagger.Component
//import dagger.android.AndroidInjector
//import dagger.android.support.AndroidSupportInjectionModule
//
//@Component(
//    modules = {
//        AndroidSupportInjectionModule.class,
//    }
//)
//interface ApplicationComponent : AndroidInjector<BaseApplication?> {
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application?): Builder?
//        fun build(): ApplicationComponent?
//    }
//}