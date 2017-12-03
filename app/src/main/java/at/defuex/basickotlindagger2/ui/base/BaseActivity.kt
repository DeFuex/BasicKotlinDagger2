package at.defuex.basickotlindagger2.ui.base

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by timoobereder on 03.12.17.
 */

abstract class BaseActivity : AppCompatActivity(), HasFragmentInjector {

    @Inject @field:Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    internal lateinit var fragmentManager: FragmentManager

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit()
    }
}