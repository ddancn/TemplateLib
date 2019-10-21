package com.ddancn.templatelib;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ddancn.templatelib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ddan.zhuang
 * @date 2019/10/21
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.nav_view)
    BottomNavigationView mBottomNavView;

    private long lastBackTime;
    private static final long BACK_PRESSED_DURATION = 2000L;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean hasHeader() {
        return false;
    }

    @Override
    protected void initView() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DemoFragment.newInstance(getString(R.string.title_home)));
        adapter.addFragment(DemoFragment.newInstance(getString(R.string.title_dashboard)));
        adapter.addFragment(DemoFragment.newInstance(getString(R.string.title_notifications)));
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void bindListener() {
        mBottomNavView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0, false);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1, false);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2, false);
                    return true;
                default:
            }
            return false;
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                    mBottomNavView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        long timeDelta = now - lastBackTime;
        lastBackTime = now;
        if (timeDelta > BACK_PRESSED_DURATION) {
            toast(R.string.press_again_to_exit);
        } else {
            super.onBackPressed();
        }
    }

    public class MainPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();

        MainPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        @NonNull
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }
    }

}
