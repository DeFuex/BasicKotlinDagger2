package at.defuex.basickotlindagger2.di.module

import android.app.Application
import at.defuex.basickotlindagger2.App
import at.defuex.basickotlindagger2.di.inject.PerActivity
import at.defuex.basickotlindagger2.ui.main.MainActivity
import at.defuex.basickotlindagger2.ui.main.MainActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by timoobereder on 03.12.17.
 */

@Module(includes = arrayOf(AndroidInjectionModule::class, NetworkModule::class))
abstract class AppModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun mainActivityInjector() : MainActivity

    @Binds
    @Singleton
    internal abstract
    fun application(app: App): Application
}