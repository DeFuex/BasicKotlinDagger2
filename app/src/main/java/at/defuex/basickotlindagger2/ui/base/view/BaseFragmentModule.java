package at.defuex.basickotlindagger2.ui.base.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;

import javax.inject.Named;

import at.defuex.basickotlindagger2.di.inject.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by timoobereder on 03.12.17.
 */

@Module
public abstract class BaseFragmentModule {

    public static final String FRAGMENT = "BaseFragmentModule.fragment";

    static final String CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager";

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    @PerFragment
    static FragmentManager childFragmentManager(@Named(FRAGMENT) Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return fragment.getChildFragmentManager();
        }
        return null;
    }

}
