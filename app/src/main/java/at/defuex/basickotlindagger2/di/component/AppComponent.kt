package at.defuex.basickotlindagger2.di.component


import at.defuex.basickotlindagger2.App
import at.defuex.basickotlindagger2.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by timoobereder on 03.12.17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}