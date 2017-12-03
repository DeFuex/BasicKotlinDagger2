package at.defuex.basickotlindagger2.ui.main

import android.app.Activity
import at.defuex.basickotlindagger2.di.inject.PerActivity
import at.defuex.basickotlindagger2.di.inject.PerFragment
import at.defuex.basickotlindagger2.ui.base.BaseActivityModule
import at.defuex.basickotlindagger2.ui.main.view.MainFragment
import at.defuex.basickotlindagger2.ui.main.view.MainFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by timoobereder on 03.12.17.
 */

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class MainActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    internal abstract fun mainFragmentInjector(): MainFragment

    @Binds
    @PerActivity
    internal abstract fun activity(mainActivity: MainActivity): Activity

}