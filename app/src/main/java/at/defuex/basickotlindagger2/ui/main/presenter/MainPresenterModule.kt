package at.defuex.basickotlindagger2.ui.main.presenter

import at.defuex.basickotlindagger2.di.inject.PerFragment
import dagger.Binds
import dagger.Module

/**
 * Created by timoobereder on 03.12.17.
 */

@Module
abstract class MainPresenterModule {

    @Binds
    @PerFragment
    internal abstract fun mainPresenter(mainPresenterImpl: MainPresenterImpl): MainPresenter
}