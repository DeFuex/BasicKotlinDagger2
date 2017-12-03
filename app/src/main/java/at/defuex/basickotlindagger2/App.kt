package at.defuex.basickotlindagger2

import android.app.Activity
import android.app.Application
import at.defuex.basickotlindagger2.di.component.AppComponent
import at.defuex.basickotlindagger2.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by timoobereder on 03.12.17.
 */

class App : Application(), HasActivityInjector {

    val appComponent: AppComponent? = null

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().create(this).inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector;
    }
}