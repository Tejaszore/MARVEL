package com.example.marvel.di

import com.example.marvel.BaseApplication
import dagger.Component
import javax.inject.Singleton

//@ForApplication
@Singleton
@Component(
    modules = [AppModule::class]
)
interface ApplicationComponent {

    fun inject(application: BaseApplication)
}


//@Singleton
//@Component(modules={AppModule.class, NetModule.class})
//public interface AppComponent {
//    void inject(MainActivity activity);
//    // void inject(MyFragment fragment);
//    // void inject(MyService service);
//}