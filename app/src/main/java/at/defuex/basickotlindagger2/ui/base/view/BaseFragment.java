package at.defuex.basickotlindagger2.ui.base.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Created by timoobereder on 03.12.17.
 */

public abstract class BaseFragment extends Fragment implements HasFragmentInjector {

    @Inject
    protected Context activityContext;

    @Inject
    @Named(BaseFragmentModule.CHILD_FRAGMENT_MANAGER)
    protected FragmentManager childFragmentManager;

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Nullable
    private Unbinder viewUnbinder;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    // it's important to use onViewStateRestored instead of other lifecycle methods here
    // because views, and other things like reources and listeners that are bound by butterknife
    // will be null except when onViewStateRestored is reached.
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        View view = getView();

        if (view != null) {
            viewUnbinder = ButterKnife.bind(this, view);
        }
    }

    @Override
    public void onDestroyView() {
        if (viewUnbinder != null) {
            viewUnbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public final AndroidInjector<Fragment> fragmentInjector() {
        return childFragmentInjector;
    }

    protected final void addChildFragment(int containerViewId, Fragment fragment) {
        childFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }
}
