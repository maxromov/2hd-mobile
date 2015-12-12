package uzap.com.ua.twohourdelivery.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import uzap.com.ua.twohourdelivery.activity.MainActivity;

public abstract class CommonFragment extends Fragment {

    protected void addFragment(CommonFragment f) {
        ((MainActivity) getActivity()).addFragment(f);
    }

    protected void replaceFragment(CommonFragment f) {
        ((MainActivity) getActivity()).replaceFragment(f);
    }

    protected void popAll() {
        getFragmentManager().popBackStackImmediate(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    protected void popFragment() {
        getFragmentManager().popBackStack();
    }

    public abstract String getTitle();

    protected void goneFab() {
        ((MainActivity) getActivity()).goneFab();
    }

    protected void showFab() {
        ((MainActivity) getActivity()).showFab();
    }

}
