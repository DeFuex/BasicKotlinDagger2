package at.defuex.basickotlindagger2.ui.main.view

import android.app.Fragment
import at.defuex.basickotlindagger2.di.inject.PerFragment
import at.defuex.basickotlindagger2.ui.base.view.BaseFragmentModule
import at.defuex.basickotlindagger2.ui.main.presenter.MainPresenterModule
import dagger.Binds
import dagger.Module
import javax.inject.Named

/**
 * Created by timoobereder on 03.12.17.
 */

@Module(includes = arrayOf(BaseFragmentModule::class, MainPresenterModule::class))
abstract class MainFragmentModule {

    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    internal abstract fun fragment(mainFragment: MainFragment): Fragment

    @Binds
    @PerFragment
    internal abstract fun mainView(mainFragment: MainFragment): MainView
}