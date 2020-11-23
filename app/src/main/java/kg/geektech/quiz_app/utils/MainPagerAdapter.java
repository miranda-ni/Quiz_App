package kg.geektech.quiz_app.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import kg.geektech.quiz_app.ui.history.HistoryFragment;
import kg.geektech.quiz_app.ui.main.MainFragment;
import kg.geektech.quiz_app.ui.settings.SettingsFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 1:

                fragment = HistoryFragment.newInstance();
                break;
            case 2:
                fragment = SettingsFragment.newInstance();

                break;
            default:
                fragment = MainFragment.newInstance();

                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
