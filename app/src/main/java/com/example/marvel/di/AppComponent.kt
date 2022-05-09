package com.example.marvel.di

import com.example.marvel.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@ForApplication
@Singleton
@Component
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AppModule): Builder
        fun build(): AppComponent
    }
}

//@Singleton
//@Component(modules = [AndroidInjectionModule::class, AppModule::class])
//interface AppComponent : AndroidInjector<BaseApplication> {
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//        fun build(): AppComponent
//    }
//    override fun inject(app: BaseApplication)
//}

//@ForApplication
//@Singleton
//@Component(
//    modules = [AppModule::class]
//)
//interface AppComponent {
//    fun inject(application: BaseApplication)
//}

////@ForApplication
//@Component(modules = [AppModule::class])
//interface ApplicationComponent {
//
//    fun inject(application: BaseApplication)
//}


//@Singleton
//@Component(modules={AppModule.class, NetModule.class})
//public interface AppComponent {
//    void inject(MainActivity activity);
//    // void inject(MyFragment fragment);
//    // void inject(MyService service);
//}